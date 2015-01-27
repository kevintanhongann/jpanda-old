package org.jpanda;

import org.jpanda.config.ApplicationProperties;
import org.jpanda.web.RedirectsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebSecurity
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class App
{
    public static void main(final String... args)
    {
        SpringApplication.run(App.class, args);
    }

    @Configuration
    public static class WebConfig extends WebMvcConfigurerAdapter
    {
        @Autowired
        private RedirectsInterceptor redirectsInterceptor;

        @Override
        public void addInterceptors(final InterceptorRegistry registry)
        {
            registry.addInterceptor(redirectsInterceptor).excludePathPatterns("/api/**", "/admin/**", "/error");
        }

        @Override
        public void addViewControllers(final ViewControllerRegistry registry)
        {
            registry.addViewController("/admin/login").setViewName("admin/login");
            registry.addViewController("/admin").setViewName("admin/index");
            registry.setOrder(-1);
        }
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    public static class SecurityConfig extends WebSecurityConfigurerAdapter
    {
        @Override
        protected void configure(final HttpSecurity http) throws Exception
        {
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().authenticated().and()
                    .formLogin().loginPage("/admin/login").permitAll().and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                    .logoutSuccessUrl("/admin/login?logout");
        }

        @Override
        protected void configure(final AuthenticationManagerBuilder auth) throws Exception
        {
            auth.inMemoryAuthentication()
                    .withUser("admin").password("admin").roles("USER", "ADMIN");
        }
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1)
    public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter
    {
        @Override
        protected void configure(final HttpSecurity http) throws Exception
        {
            http.antMatcher("/api/**").authorizeRequests().anyRequest().hasRole("ADMIN");
        }
    }
}
