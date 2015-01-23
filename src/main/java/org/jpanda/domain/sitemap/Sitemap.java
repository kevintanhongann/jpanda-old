package org.jpanda.domain.sitemap;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.LinkedList;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "urlset")
public class Sitemap
{
    @XmlElements({@XmlElement(name = "url")})
    private final Collection<SitemapURL> elements = new LinkedList<>();

    public void addSitemapURL(final SitemapURL element)
    {
        elements.add(element);
    }

    public Collection<SitemapURL> getUrlSet()
    {
        return elements;
    }
}
