package org.jpanda.services;

import org.jpanda.config.ApplicationProperties;
import org.jpanda.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class JpaPostServiceImpl implements PostService
{
    @Autowired
    private ApplicationProperties config;

    @Override
    public Page<Post> findAll(int pageNum)
    {
        return new PageImpl<Post>(Collections.emptyList());
    }

    @Override
    public Page<Post> findByDateRange(Date start, Date end, int pageNum)
    {
        return new PageImpl<Post>(Collections.emptyList());
    }

    @Override
    public Post findBySlug(String slug)
    {
        return null;
    }
}
