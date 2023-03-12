package hookah.warehouse.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaAuditing
public class Main {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
