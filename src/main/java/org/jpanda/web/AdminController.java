package org.jpanda.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @RequestMapping()
    public String index()
    {
        return "admin/index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "admin/login";
    }

    @RequestMapping("/analytics")
    @Secured({"ROLE_ANALYTICS", "ROLE_ADMIN"})
    public String analytics()
    {
        return "admin/analytics";
    }

    @RequestMapping("/administration")
    @Secured("ROLE_ADMIN")
    public String administration()
    {
        return "admin/administration";
    }

    @RequestMapping("/partial/{page}")
    public String partial(@PathVariable("page") final String page)
    {
        return String.format("admin/partial/%s", page);
    }
}
