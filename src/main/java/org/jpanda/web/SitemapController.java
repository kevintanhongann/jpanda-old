package org.jpanda.web;

import org.jpanda.config.ApplicationProperties;
import org.jpanda.domain.Post;
import org.jpanda.domain.sitemap.Sitemap;
import org.jpanda.domain.sitemap.SitemapURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

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

    @RequestMapping(value = "${panda.urls.sitemap}", produces = "application/xml")
    public Sitemap sitemap(final HttpServletRequest request)
    {
        final UriComponentsBuilder builder =
                ServletUriComponentsBuilder.fromRequest(request).replacePath(config.getUrls().get("slug"));
        final Sitemap sitemap = new Sitemap();

        fetchSitemapPosts().forEach((post) -> {
            final SitemapURL url = new SitemapURL(builder.buildAndExpand(post.getSlug()).encode().toUriString());

            if (config.getSitemap().isChangeFrequency())
            {
                url.setChangeFrequency(config.getSitemap().getChangeFrequency());
            }

            if (config.getSitemap().isPriority())
            {
                url.setPriority(config.getSitemap().getPriority());
            }

            sitemap.addSitemapURL(url);
        });

        return sitemap;
    }

    private List<Post> fetchSitemapPosts()
    {
        return Collections.emptyList();
    }
}
