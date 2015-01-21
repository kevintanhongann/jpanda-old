package org.jpanda;

import org.jpanda.web.RedirectsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{
    @Autowired
    private RedirectsInterceptor redirectsInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry)
    {
        registry.addInterceptor(redirectsInterceptor).excludePathPatterns("/api/**", "/admin/**");
    }
}
