package org.jpanda.web;

import org.jpanda.domain.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController
{
    @ModelAttribute
    public Post findPost(@PathVariable("slug") final String slug)
    {
        return new Post();
    }

    @RequestMapping("/{slug:(?!\\d\\d\\d\\d$).+}")
    public String show(@ModelAttribute final Post post)
    {
        return "post";
    }

    @RequestMapping("/preview/{slug}/{code}")
    public String preview(@ModelAttribute final Post post, @PathVariable("code") final String code)
    {
        return "post";
    }
}
