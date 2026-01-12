package com.example.barangayconnect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // ⭐ MAIN HANDLER
        // Serves all files stored directly in the /uploads/ folder
        // Supports spaces + encoded URLs, works for PNG/JPG/PDF
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:./uploads/")
                .setCachePeriod(0)
                .resourceChain(false);  // prevent blocking filenames with spaces
    }
}
