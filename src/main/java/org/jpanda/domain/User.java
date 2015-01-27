package org.jpanda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity representing a system user (a posts author)
 *
 * @author T-PWK
 */
@Entity
public class User
{
    /**
     * User identifier
     */
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String email;
    private String password;
    private String website;
    private String location;
    private String bio;
    private String image;
    private boolean lead;

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public boolean isLead()
    {
        return lead;
    }

    public void setLead(boolean lead)
    {
        this.lead = lead;
    }
}
