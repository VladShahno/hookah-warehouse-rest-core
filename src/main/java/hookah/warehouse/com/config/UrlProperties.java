package hookah.warehouse.com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("url")
@Data
public class UrlProperties {

  private String base;
}
