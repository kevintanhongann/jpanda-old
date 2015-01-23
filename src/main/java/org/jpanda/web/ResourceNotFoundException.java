package org.jpanda.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception thrown when a specific resource has not been found. It results in 404 response status code.
 *
 * @see org.springframework.http.HttpStatus#NOT_FOUND
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
}
