package com.example.demo.config;

import com.example.demo.scalar.MonthScalar;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@Configuration
public class GraphqlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Date).scalar(MonthScalar.INSTANCE);
    }

    @Bean
    public AuditorAware<String> createAuditorProvider() {
        return new ErpAuditorAware();
    }

}
