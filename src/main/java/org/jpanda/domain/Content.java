package org.jpanda.domain;

import javax.persistence.*;

/**
 * Entity representing large text
 *
 * @author T-PWK
 */
@Entity
public class Content
{
    /**
     * Content identifier
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Content text
     */
    @Lob
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
