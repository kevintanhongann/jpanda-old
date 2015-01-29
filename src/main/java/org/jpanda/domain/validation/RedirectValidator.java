package org.jpanda.domain.validation;

import org.jpanda.domain.Redirect;
import org.jpanda.domain.RedirectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RedirectValidator implements Validator
{
    @Autowired
    private RedirectRepository repository;

    @Override
    public boolean supports(final Class<?> clazz)
    {
        return clazz.isAssignableFrom(Redirect.class);
    }

    @Override
    public void validate(final Object target, final Errors errors)
    {
        if (errors.hasErrors())
        {
            return;
        }

        final String uri = ((Redirect) target).getFrom();

        if (repository.findByFromUrl(uri) != null)
        {
            errors.rejectValue("from", "RedirectValidator", new String[]{uri},
                    String.format("Redirect for [%s] already exists", uri));
        }
    }
}
