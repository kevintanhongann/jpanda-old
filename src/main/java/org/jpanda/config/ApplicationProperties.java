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

    /**
     * Sitemap configuration
     *
     * @see <a href="https://support.google.com/webmasters/answer/183668?hl=en&ref_topic=6080646&rd=1">Build your sitemap</a>
     */
    public static class Sitemap
    {
        /**
         * Default sitemap url priority
         */
        private float priority = -1f;

        /**
         * Default sitemap url change frequency
         *
         * @see org.jpanda.domain.sitemap.ChangeFrequency
         */
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
