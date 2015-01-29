package org.jpanda.web;

import org.jpanda.config.ApplicationProperties;
import org.jpanda.domain.Post;
import org.jpanda.domain.sitemap.Sitemap;
import org.jpanda.domain.sitemap.SitemapURL;
import org.jpanda.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller generating sitemap.xml
 *
 * @author T-PWK
 */
@RestController
public class SitemapController
{
    @Autowired
    private ApplicationProperties config;

    @Autowired
    private PostService postService;

    /**
     * Builds website sitemap in XML format
     *
     * @param request current request
     * @return sitemap instance
     */
    @RequestMapping(value = "${panda.urls.sitemap}", produces = "application/xml")
    public Sitemap sitemap(final HttpServletRequest request)
    {
        final UriComponentsBuilder builder =
                ServletUriComponentsBuilder.fromRequest(request).replacePath(config.getUrls().get("slug"));

        final List<SitemapURL> urls = fetchSitemapPosts().stream()
                .map(post -> {
                    final SitemapURL sitemap = new SitemapURL();

                    sitemap.setLocation(builder.buildAndExpand(post.getSlug()).encode().toUriString());
                    sitemap.setLastModified(post.getUpdatedAt());

                    return sitemap;
                })
                .collect(Collectors.toList());

        if (config.getSitemap().isChangeFrequency())
        {
            urls.forEach(url -> url.setChangeFrequency(config.getSitemap().getChangeFrequency()));
        }

        if (config.getSitemap().isPriority())
        {
            urls.forEach(url -> url.setPriority(config.getSitemap().getPriority()));
        }

        return new Sitemap(urls);
    }

    /**
     * Fetches a list of posts from the post repository
     *
     * @return a list of posts or empty list if there is no posts in the system
     */
    private List<Post> fetchSitemapPosts()
    {
        return postService.findAll(0).getContent();
    }
}
