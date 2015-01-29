package org.jpanda.web.api.v1;

import org.jpanda.domain.Redirect;
import org.jpanda.domain.RedirectRepository;
import org.jpanda.web.DuplicateEntityException;
import org.jpanda.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Redirects API controller
 */
@RestController
@RequestMapping("/api/v1/redirects")
public class RedirectApiController
{
    /**
     * Redirects repository
     */
    @Autowired
    private RedirectRepository repository;

    /**
     * Returns all redirects using given pagination
     *
     * @param pageable pagination details
     * @return subset of all {@code Redirect} instances
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<Redirect> allRedirects(final Pageable pageable)
    {
        return repository.findAll(pageable);
    }

    /**
     * Create new {@code Redirect} instance
     *
     * @param redirect {@code Redirect} instance to be created
     * @param result   input {@code Redirect} instance validation results
     * @return newly created {@code Redirect} instance
     * @throws BindException            if there was any validation issue with the input {@code Redirect}
     * @throws DuplicateEntityException if there is a redirect entity with the same {@code Redirect#getFrom from} URI
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Redirect create(@RequestBody @Valid final Redirect redirect, final BindingResult result)
            throws BindException, DuplicateEntityException
    {
        if (result.hasErrors())
        {
            throw new BindException(result);
        }

        if (repository.findByFromUrl(redirect.getFrom()) != null)
        {
            throw new DuplicateEntityException(String.format("Redirect for [%s] already exists", redirect.getFrom()));
        }

        return repository.save(redirect);
    }

    /**
     * Returns {@code Redirect} instance by the given entity identifier
     *
     * @param id entity identifier
     * @return {@code Redirect} instance
     * @throws org.jpanda.web.ResourceNotFoundException if there is no instance with the given identifier
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Redirect findById(@PathVariable("id") final long id)
    {
        checkRedirect(id);
        return repository.findOne(id);
    }

    /**
     * Deletes {@code Redirect} instance by the given entity identifier
     *
     * @param id entity identifier
     * @throws org.jpanda.web.ResourceNotFoundException if there is no instance with the given identifier
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") final long id)
    {
        checkRedirect(id);
        repository.delete(id);
    }

    /**
     * Checks if a redirect entity with the given identifier exists.
     *
     * @param id redirect entity identifier
     * @throws org.jpanda.web.ResourceNotFoundException if redirect entity does not exist for the given identifier
     */
    private void checkRedirect(final long id)
    {
        if (!repository.exists(id))
        {
            throw new ResourceNotFoundException(String.format("No redirect with id:[%d] exists!", id));
        }
    }
}
