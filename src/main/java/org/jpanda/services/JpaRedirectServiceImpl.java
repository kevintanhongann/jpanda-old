package org.jpanda.services;

import org.jpanda.domain.Redirect;
import org.jpanda.domain.RedirectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JpaRedirectServiceImpl implements RedirectService
{
    @Autowired
    private RedirectRepository redirectRepository;

    @Override
    public Redirect findByUri(final String uri)
    {
        return redirectRepository.findByFromUrl(Objects.requireNonNull(uri));
    }
}
