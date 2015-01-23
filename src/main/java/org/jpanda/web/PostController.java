package org.jpanda.web;

import org.jpanda.domain.Post;
import org.jpanda.domain.PostType;
import org.jpanda.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.EnumMap;

@Controller
public class PostController
{
    /**
     * Mapping of page type to page template
     */
    private static EnumMap<PostType, String> TYPE_TEMPLATE_MAP = new EnumMap<>(PostType.class);

    static
    {
        TYPE_TEMPLATE_MAP.put(PostType.PAGE, "page");
        TYPE_TEMPLATE_MAP.put(PostType.POST, "post");
    }

    @Autowired
    private PostService postService;

    @ModelAttribute
    public Post findPost(@PathVariable("slug") final String slug)
    {
        return postService.findBySlug(slug);
    }

    @RequestMapping("/{slug:(?!\\d\\d\\d\\d$).+}")
    public String show(final Post post)
    {
        if (post == null || post.getType() == null || !post.isLive())
        {
            throw new ResourceNotFoundException();
        }

        return TYPE_TEMPLATE_MAP.get(post.getType());
    }

    @RequestMapping("/preview/{slug}/{code}")
    public String preview(final Post post, @PathVariable("code") final String code, final Model model)
    {
        if (post == null)
        {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("preview", true);

        return TYPE_TEMPLATE_MAP.get(post.getType());
    }
}
