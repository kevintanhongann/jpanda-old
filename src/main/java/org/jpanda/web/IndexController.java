package org.jpanda.web;

import org.jpanda.config.ApplicationProperties;
import org.jpanda.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * Posts listing controller
 *
 * @author T-PWK
 */
@Controller
public class IndexController
{
    @Autowired
    private ApplicationProperties config;

    @Autowired
    private PostService postService;

    /**
     * Handles index (/) page
     *
     * @param model model passed to a view
     * @return posts listing view
     */
    @RequestMapping("${panda.urls.index}")
    public String index(final Model model)
    {
        return "index";
    }

    /**
     * Handles pagination of index page (e.g. /page/2)
     *
     * @param page  page number (1-based number)
     * @param model model passed to a view
     * @return posts listing view or a redirect URL
     */
    @RequestMapping("${panda.urls.index-page}")
    public String indexAtPage(@PathVariable("page") final int page, final Model model)
    {
        if (page < 2)
        {
            return redirectWithUrl("index");
        }
        return "index";
    }

    /**
     * Handles search for posts published in a given year (e.g. /2012)
     *
     * @param year  year used for post search
     * @param model model passed to a view
     * @return posts listing view
     */
    @RequestMapping("${panda.urls.year}")
    public String listByYear(@PathVariable("year") final int year, final Model model)
    {
        return populatePostsByDate(year, -1, -1, 0, model);
    }

    /**
     * Handles search for posts published in a given year with pagination (e.g. /2012/page/2)
     *
     * @param year  year used for post search
     * @param page  pagination (1-based number)
     * @param model model passed to a view
     * @return posts listing view or a redirect URL
     */
    @RequestMapping("${panda.urls.year-page}")
    public String listByYearAtPage(@PathVariable("year") final int year, @PathVariable("page") final int page,
                                   final Model model)
    {
        if (page < 2)
        {
            return redirectWithUrl("year-redirect");
        }
        return populatePostsByDate(year, -1, -1, page - 1, model);
    }

    /**
     * Handles search for posts published in a given year and month (e.g. /2012/12 - December 2012)
     *
     * @param year  year used for post search
     * @param month month used for post search
     * @param model model passed to a view
     * @return posts listing view
     */
    @RequestMapping("${panda.urls.month}")
    public String listByMonth(@PathVariable("year") final int year, @PathVariable("month") final int month,
                              final Model model)
    {
        return populatePostsByDate(year, month, -1, 0, model);
    }

    /**
     * Handles search for posts published in a given year and month with pagination (e.g. /2012/12/page/2 - December
     * 2012, page two)
     *
     * @param year  year used for post search
     * @param month month used for post search
     * @param page  pagination (1-based number)
     * @param model model passed to a view
     * @return posts listing view or a redirect URL
     */
    @RequestMapping("${panda.urls.month-page}")
    public String listByMonthAtPage(@PathVariable("year") final int year, @PathVariable("month") final int month,
                                    @PathVariable("page") final int page, final Model model)
    {
        if (page < 2)
        {
            return redirectWithUrl("month-redirect");
        }
        return populatePostsByDate(year, month, -1, page - 1, model);
    }

    /**
     * Handles search for posts published in a given year, month and a day (e.g. /2012/12/20 - 20th December 2012)
     *
     * @param year  year used for post search
     * @param month month used for post search
     * @param day   day used for post search
     * @param model model passed to a view
     * @return posts listing view
     */
    @RequestMapping("${panda.urls.day}")
    public String listByDay(@PathVariable("year") final int year, @PathVariable("month") final int month,
                            @PathVariable("day") final int day, final Model model)
    {
        return populatePostsByDate(year, month, day, 0, model);
    }

    /**
     * Handles search for posts published in a given year, month and a day with pagination (e.g. /2012/12/20/page/2 -
     * 20th December 2012, second page of results)
     *
     * @param year  year used for post search
     * @param month month used for post search
     * @param day   day used for post search
     * @param page  pagination (1-based number)
     * @param model model passed to a view
     * @return posts listing view or a redirect URL
     */
    @RequestMapping("${panda.urls.day-page}")
    public String listByDayAtPage(@PathVariable("year") final int year, @PathVariable("month") final int month,
                                  @PathVariable("day") final int day, @PathVariable("page") final int page,
                                  final Model model)
    {
        if (page < 2)
        {
            return redirectWithUrl("day-redirect");
        }

        return populatePostsByDate(year, month, day, page - 1, model);
    }

    /**
     * Handles search for posts by label (e.g. /search/label/java)
     *
     * @param label label text
     * @param model model passed to a view
     * @return posts listing view
     */
    @RequestMapping("${panda.urls.labels}")
    public String listByLabel(@PathVariable("label") final String label, final Model model)
    {
        return "index";
    }

    /**
     * Handles pagination of search for posts by label (e.g. /search/label/java/page/2)
     *
     * @param label label text
     * @param page  pagination (1-based number)
     * @param model model passed to a view
     * @return posts listing view or a redirect URL
     */
    @RequestMapping("${panda.urls.labels-page}")
    public String listByLabelAtPage(@PathVariable("label") final String label, @PathVariable("page") final int page,
                                    final Model model)
    {
        if (page < 2)
        {
            return redirectWithUrl("labels");
        }
        return "index";
    }

    private String redirectWithUrl(final String key)
    {
        return "redirect:" + config.getUrls().get(key);
    }

    private String allPosts(final int page, final Model model)
    {
        model.addAttribute("posts", postService.findAll(page));

        return "index";
    }

    private String populatePostsByDate(final int year, final int month, final int day, final int page, final Model model)
    {
        LocalDateTime start = LocalDateTime.now().with(LocalTime.MIN).withYear(year);

        if (month > 0)
        {
            start = start.withMonth(month);
        }
        if (day > 0)
        {
            start = start.withDayOfMonth(day);
        }

        LocalDateTime end = start.with(LocalTime.MAX);

        if (day < 0 && month > 0)
        {
            start = start.with(firstDayOfMonth());
            end = end.with(lastDayOfMonth());
        }
        else if (day < 0 && month < 0)
        {
            start = start.with(firstDayOfYear());
            end = end.with(lastDayOfYear());
        }

        final Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
        final Date endDate = Date.from(end.atZone(ZoneId.systemDefault()).toInstant());

        model.addAttribute("posts", postService.findByDateRange(startDate, endDate, page));

        return "index";
    }
}
