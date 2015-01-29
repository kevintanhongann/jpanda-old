package org.jpanda.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception thrown when duplicate resource was found. That exception is thrown mainly during entity creation.
 * It results in 400 (Bad Request) response status code.
 *
 * @see org.springframework.http.HttpStatus#BAD_REQUEST
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEntityException extends RuntimeException
{
    public DuplicateEntityException()
    {
    }

    public DuplicateEntityException(String message)
    {
        super(message);
    }
}
