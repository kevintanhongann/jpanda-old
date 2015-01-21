package org.jpanda;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "panda")
public class ApplicationProperties
{
    /**
     * Number of posts per page
     */
    private int postsPerPage;

    private Map<String, String> urls = new HashMap<>();

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
}
