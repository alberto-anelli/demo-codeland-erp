package it.codeland.support.managementcontrol.config;

import it.codeland.support.managementcontrol.scalar.MonthScalar;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

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
