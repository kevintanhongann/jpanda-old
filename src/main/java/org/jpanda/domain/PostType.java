package org.jpanda.domain;

/**
 * Specifies a type of a post
 *
 * @author T-PWK
 */
public enum PostType
{
    /**
     * Represents a post.
     * <p>
     * A page can be accessed directly by a page URL (slug). It appear in a listing pages e.g. all posts, posts by
     * label, posts in a date range etc.
     */
    POST,

    /**
     * Represents a page.
     * <p>
     * A page can be accessed directly by a page URL (slug). It does not appear in a listing pages.
     */
    PAGE
}
