package kth.iv1201.group9.recruitment_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is responsible for creating a bean that represents a map of tokens to email addresses. The map is used for password recovery.
 */
@Configuration
public class MapConfig {

    /**
     * Creates a bean that represents a map of tokens to email addresses.
     *
     * @return The map of tokens to email addresses.
     */
    @Bean
    Map<String, String> tokenEmailMap() {
        return new ConcurrentHashMap<>();
    }
}
