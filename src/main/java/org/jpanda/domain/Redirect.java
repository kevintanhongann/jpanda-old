package org.jpanda.domain;

/**
 * Created by tbs on 21/01/2015.
 */
public class Redirect
{
    private long id;

    private String toUrl;

    private String fromUrl;

    private RedirectType type;

    public RedirectType getType()
    {
        return type;
    }

    public void setType(RedirectType type)
    {
        this.type = type;
    }

    public String getTo()
    {
        return toUrl;
    }

    public void setTo(String to)
    {
        this.toUrl = to;
    }

    public String getFrom()
    {
        return fromUrl;
    }

    public void setFrom(String from)
    {
        this.fromUrl = from;
    }
}
