package kr.co.estate;

import kr.co.estate.config.properties.JsonFilesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties({JsonFilesProperties.class })
public class EstateApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EstateApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EstateApplication.class);
    }
}
