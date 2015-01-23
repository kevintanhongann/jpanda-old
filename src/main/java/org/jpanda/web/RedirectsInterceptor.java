package org.jpanda.web;

import org.jpanda.domain.Redirect;
import org.jpanda.services.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromRequest;

/**
 * Performs redirect or internal forward if there is a redirect item configured for a given request URI.
 * <p/>
 * This component handles three types of redirects:
 * <ul>
 * <li>Internal - request is directed to a different route using {@link javax.servlet.RequestDispatcher}</li>
 * <li>HTTP 301 - <strong>Moved permanently</strong> - system sends 301 response to a client with a new location</li>
 * <li>HTTP 302 - <strong>Moved temporarily</strong> - system sends 302 response to a client with a new path</li>
 * </ul>
 *
 * @author T-PWK
 */
@Component
public class RedirectsInterceptor extends HandlerInterceptorAdapter
{
    @Autowired
    private RedirectService redirectService;

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if there is no valid redirect configured for the current request; otherwise {@code false}.
     */
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception
    {
        final Redirect redirect = findRedirectForRequest(request);

        if (redirect == null || redirect.getType() == null || redirect.getTo() == null)
        {
            return true;
        }

        switch (redirect.getType())
        {
            case INTERNAL:
                request.getRequestDispatcher(redirect.getTo()).forward(request, response);
                break;
            case HTTP_302:
                response.sendRedirect(redirect.getTo());
                break;
            case HTTP_301:
                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                response.setHeader("Location", fromRequest(request).replacePath(redirect.getTo()).toUriString());
                break;
            default:
        }

        return false;
    }

    /**
     * Looks up a redirect based on the given request URI
     *
     * @param request an HTTP request to get URI from
     * @return an redirect item found for the given request or {@code null}
     * @see javax.servlet.http.HttpServletRequest#getRequestURI()
     */
    private Redirect findRedirectForRequest(final HttpServletRequest request)
    {
        return redirectService.findByUri(request.getRequestURI());
    }
}
