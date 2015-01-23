package org.jpanda.services;

import org.jpanda.domain.Post;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface PostService
{
    Page<Post> findAll(int pageNum);

    Page<Post> findByDateRange(Date start, Date end, int pageNum);

    Post findBySlug(String slug);
}
