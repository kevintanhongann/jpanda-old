package org.jpanda.web.api.v1;

import org.jpanda.domain.Post;
import org.jpanda.domain.PostRepository;
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

@RestController
@RequestMapping("/api/v1/posts")
public class PostApiController
{
    @Autowired
    private PostRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Post> allRedirects(final Pageable pageable)
    {
        return repository.findAll(pageable);
    }

    /**
     * Create new {@code Post} instance
     *
     * @param post   {@code Post} instance to be created
     * @param result input {@code Post} instance validation results
     * @return newly created {@code Post} instance
     * @throws BindException            if there was any validation issue with the input {@code Post}
     * @throws DuplicateEntityException if there is a redirect entity with the same {@code Post#getSlug slug}
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody @Valid final Post post, final BindingResult result)
            throws BindException, DuplicateEntityException
    {
        if (result.hasErrors())
        {
            throw new BindException(result);
        }

        if (repository.findBySlug(post.getSlug()) != null)
        {
            throw new DuplicateEntityException(String.format("Post for [%s] already exists", post.getSlug()));
        }

        return repository.save(post);
    }


    /**
     * Returns {@link org.jpanda.domain.Post} instance by the given entity identifier
     *
     * @param id entity identifier
     * @return {@link org.jpanda.domain.Post} instance
     * @throws org.jpanda.web.ResourceNotFoundException if there is no instance with the given identifier
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post findById(@PathVariable("id") final long id)
    {
        checkPost(id);
        return repository.findOne(id);
    }


    /**
     * Deletes {@code Post} instance by the given entity identifier
     *
     * @param id entity identifier
     * @throws org.jpanda.web.ResourceNotFoundException if there is no instance with the given identifier
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") final long id)
    {
        checkPost(id);
        repository.delete(id);
    }

    /**
     * Checks if a post entity with the given identifier exists.
     *
     * @param id post entity identifier
     * @throws org.jpanda.web.ResourceNotFoundException if {@link org.jpanda.domain.Post} entity does not exist
     *                                                  for the given identifier
     */
    private void checkPost(final long id)
    {
        if (!repository.exists(id))
        {
            throw new ResourceNotFoundException(String.format("No post with id:[%d] exists!", id));
        }
    }

}
