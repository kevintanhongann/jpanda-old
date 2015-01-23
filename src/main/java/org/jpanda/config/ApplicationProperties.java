package org.jpanda.config;

import org.jpanda.domain.sitemap.ChangeFrequency;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "panda")
public class ApplicationProperties
{
    /**
     * Number of posts per page
     */
    private int postsPerPage;

    /**
     * Website URLs
     */
    private Map<String, String> urls;

    /**
     * Sitemap configuration
     */
    private Sitemap sitemap = new Sitemap();

    public int getPostsPerPage()
    {
        return postsPerPage;
    }

    public void setPostsPerPage(int postsPerPage)
    {
        this.postsPerPage = postsPerPage;
    }

    public Map<String, String> getUrls()
    {
        return urls;
    }

    public void setUrls(Map<String, String> urls)
    {
        this.urls = urls;
    }

    public Sitemap getSitemap()
    {
        return sitemap;
    }

    public void setSitemap(Sitemap sitemap)
    {
        this.sitemap = sitemap;
    }

    public static class Sitemap
    {
        private float priority = -1f;

        private ChangeFrequency changeFrequency;

        public float getPriority()
        {
            return priority;
        }

        public void setPriority(float priority)
        {
            this.priority = priority;
        }

        public ChangeFrequency getChangeFrequency()
        {
            return changeFrequency;
        }

        public void setChangeFrequency(ChangeFrequency changeFrequency)
        {
            this.changeFrequency = changeFrequency;
        }
        
        public boolean isChangeFrequency()
        {
            return changeFrequency != null;
        }

        public boolean isPriority()
        {
            return priority > 0f;
        }
    }
}
