package com.test.note.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
    public class MvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/resources/templates/add_note").setViewName("add_note");
//        registry.addViewController("/note").setViewName("note");
//    }
    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        registry.jsp("/resources/templates/", ".jsp");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
