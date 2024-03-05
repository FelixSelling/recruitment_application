package kth.iv1201.group9.recruitment_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MapConfig {

  @Bean
  public Map<String, String> tokenEmailMap() {
    return new ConcurrentHashMap<>();
  }
}
