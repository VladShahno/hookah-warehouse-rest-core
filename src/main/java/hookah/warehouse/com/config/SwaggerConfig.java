package hookah.warehouse.com.config;

import java.util.Collections;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components()
            .addSecuritySchemes("oauth2", new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .flows(new OAuthFlows()
                    .implicit(new OAuthFlow()
                        .authorizationUrl("http://localhost:8080/auth/realms/hookah-cat/protocol/openid-connect/auth")
                        .scopes(new Scopes().addString("openid", "openid scope"))))
            )
        )
        .security(Collections.singletonList(new SecurityRequirement().addList("oauth2")))
        .info(new Info()
            .title("My API")
            .version("1.0.0")
        );
  }
}
