package org.jpanda.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a post
 *
 * @author T-PWK
 */
public class Post
{
    /**
     * Post identifier
     */
    private long id;

    private String slug;

    private String title;

    private Content content;
    private Content source;
    private Content teaser;

    private PostType type;

    private Set<String> labels = new HashSet<>();

    private User author;

    private String metaDescription;

    private String metaTitle;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedAt;

    public Post()
    {
    }

    public Post(String slug)
    {
        this.slug = slug;
    }

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public PostType getType()
    {
        return type;
    }

    public void setType(PostType type)
    {
        this.type = type;
    }

    public boolean isLive()
    {
        return false;
    }

    public String getContentText()
    {
        return content == null ? null : content.getText();
    }

    public void setContentText(String text)
    {
        if (content == null)
        {
            content = new Content();
        }
        content.setText(text);
    }

    public String getSourceText()
    {
        return source == null ? null : source.getText();
    }

    public void setSourceText(String text)
    {
        if (source == null)
        {
            source = new Content();
        }
        source.setText(text);
    }

    public String getTeaserText()
    {
        return teaser == null ? null : teaser.getText();
    }

    public void setTeaserText(String text)
    {
        if (teaser == null)
        {
            teaser = new Content();
        }
        teaser.setText(text);
    }
}
