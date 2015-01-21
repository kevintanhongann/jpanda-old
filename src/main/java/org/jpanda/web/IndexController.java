package org.jpanda.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/{year:\\d\\d\\d\\d}")
    public String listByYear(@PathVariable("year") final int year)
    {
        return "index";
    }

    @RequestMapping("/{year:\\d\\d\\d\\d}/page/{page:\\d+}")
    public String listByYearAtPage(@PathVariable("year") final int year, @PathVariable("page") final int page)
    {
        return "index";
    }

    @RequestMapping("/{year:\\d\\d\\d\\d}/{month:\\d\\d}")
    public String listByMonth(@PathVariable("year") final int year, @PathVariable("month") final int month)
    {
        return "index";
    }

    @RequestMapping("/{year:\\d\\d\\d\\d}/{month:\\d\\d}/page/{page:\\d+}")
    public String listByMonthAtPage(@PathVariable("year") final int year, @PathVariable("month") final int month,
                                    @PathVariable("page") final int page)
    {
        return "index";
    }

    @RequestMapping("/{year:\\d\\d\\d\\d}/{month:\\d\\d}/{day:\\d\\d}")
    public String listByDay(@PathVariable("year") final int year, @PathVariable("month") final int month,
                            @PathVariable("day") final int day)
    {
        return "index";
    }

    @RequestMapping("/{year:\\d\\d\\d\\d}/{month:\\d\\d}/{day:\\d\\d}/page/{page:\\d+}")
    public String listByDayAtPage(@PathVariable("year") final int year, @PathVariable("month") final int month,
                                  @PathVariable("day") final int day, @PathVariable("page") final int page)
    {
        return "index";
    }
}
