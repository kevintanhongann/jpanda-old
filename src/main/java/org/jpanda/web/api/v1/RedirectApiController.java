package org.jpanda.web.api.v1;

import org.jpanda.domain.Redirect;
import org.jpanda.domain.RedirectRepository;
import org.jpanda.domain.validation.RedirectValidator;
import org.jpanda.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Redirects API controller
 */
@RestController
@RequestMapping("/api/v1/redirects")
public class RedirectApiController
{
    @Autowired
    private RedirectRepository repository;

    @Autowired
    private RedirectValidator validator;

    @InitBinder
    protected void initBinder(final WebDataBinder binder)
    {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Redirect> allRedirects(final Pageable pageable)
    {
        return repository.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Redirect create(@RequestBody @Valid final Redirect redirect, final BindingResult result)
            throws BindException
    {
        if (result.hasErrors())
        {
            throw new BindException(result);
        }

        return repository.save(redirect);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Redirect findById(@PathVariable("id") final long id)
    {
        checkRedirect(id);
        return repository.findOne(id);
    }

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
