package org.jpanda.services;

import org.jpanda.domain.Redirect;

public interface RedirectService
{
    Redirect findByUri(String uri);
}
