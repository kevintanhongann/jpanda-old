package org.jpanda.domain;

/**
 * Redirect type
 *
 * @author T-PWK
 */
public enum RedirectType
{
    /**
     * 301 Moved Permanently
     */
    HTTP_301,

    /**
     * 302 Found - Moved Temporarily
     */
    HTTP_302,

    /**
     * Internal forward - no redirect response
     */
    INTERNAL
}
