package org.jpanda.domain;

/**
 * Entity representing large text
 *
 * @author T-PWK
 */
public class Content
{
    /**
     * Content identifier
     */
    private long id;

    /**
     * Content text
     */
    private String text;

    public long getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
