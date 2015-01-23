package org.jpanda.services;

import org.jpanda.domain.Redirect;
import org.jpanda.domain.RedirectType;
import org.springframework.stereotype.Service;

@Service
public class JpaRedirectServiceImpl implements RedirectService
{
    @Override
    public Redirect findByUri(final String uri)
    {
        if (!uri.equals("/blah"))
        {
            return null;
        }

        final Redirect redirect = new Redirect();
        redirect.setType(RedirectType.HTTP_301);
        redirect.setFrom("/blah");
        redirect.setTo("/2012");
        return redirect;
    }
}
