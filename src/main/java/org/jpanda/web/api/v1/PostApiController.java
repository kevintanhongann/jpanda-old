package org.jpanda.web.api.v1;

import org.jpanda.domain.Post;
import org.jpanda.domain.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostApiController
{
    @Autowired
    private PostRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Post> allRedirects(final Pageable pageable)
    {
        return repository.findAll(pageable);
    }

}
