package com.test.note.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
    public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/resources/templates/add_note").setViewName("add_note");
        registry.addViewController("/note").setViewName("note");
    }
    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        registry.jsp("/resources/templates/", ".jsp");
    }
}
