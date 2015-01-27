package org.jpanda.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a post
 *
 * @author T-PWK
 */
@Entity
public class Post
{
    /**
     * Post identifier
     */
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(unique = true, length = 100)
    private String slug;

    @Column(length = 250)
    private String title;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Content content;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Content source;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Content teaser;

    @Enumerated
    private PostType type;

    @ElementCollection
    private Set<String> labels = new HashSet<>();

    @ManyToOne
    private User author;

    @Column(length = 200)
    private String metaDescription;

    @Column(length = 100)
    private String metaTitle;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt = new Date();

    @Version
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
