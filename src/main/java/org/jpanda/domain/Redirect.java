package org.jpanda.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entity representing redirect
 *
 * @author T-PWK
 */
@Entity
public class Redirect
{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(length = 255, nullable = false, unique = true)
    @Pattern(regexp = "/.*")
    private String toUrl;

    @NotNull
    @Column(length = 255, nullable = false)
    @Pattern(regexp = "/.*")
    private String fromUrl;

    @NotNull
    private RedirectType type;

    public long getId()
    {
        return id;
    }

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
