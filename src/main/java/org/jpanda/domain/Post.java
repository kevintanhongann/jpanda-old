package org.jpanda.domain;

public class Post
{
    private String slug;
    private PostType type;

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
}
