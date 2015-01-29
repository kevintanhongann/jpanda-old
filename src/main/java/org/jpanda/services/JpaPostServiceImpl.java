package org.jpanda.services;

import org.jpanda.config.ApplicationProperties;
import org.jpanda.domain.Post;
import org.jpanda.domain.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JpaPostServiceImpl implements PostService
{
    @Autowired
    private PostRepository repository;

    //    private static final List<Post> posts = new ArrayList<>();
//    static
//    {
//        createPost("sailsjs-v010-express-custommiddleware", "Sails.js v0.10 express customMiddleware", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 2", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 3", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 4", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 5", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 6", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 7", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 8", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 9", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//        createPost("sailsjs-v010-express-custommiddleware-2", "Sails.js v0.10 express customMiddleware 10", "In this post, I would like to share how to customize Express server in Sails.js version 0.10. Handling of the customMiddleware has slightly changed from Sails v0.9.x. In version 0.10 that method needs to be configured in http hook (not express hook, as in the");
//    }
//
//    private static void createPost(String slug, String title, String teaser)
//    {
//        final Post post = new Post();
//        post.setSlug(slug);
//        post.setTitle(title);
//        post.setTeaserText(teaser);
//
//        posts.add(post);
//    }
//
    @Autowired
    private ApplicationProperties config;

    @Override
    public Page<Post> findAll(int pageNum)
    {
        return repository.findAll(new PageRequest(pageNum, config.getPostsPerPage()));
    }

    @Override
    public Page<Post> findByDateRange(Date start, Date end, int pageNum)
    {
        return repository.findAll(new PageRequest(pageNum, config.getPostsPerPage()));
    }

    @Override
    public Post findBySlug(String slug)
    {
        return repository.findOne(1L);
    }
}
