package rest_api_jwt_token.apis;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer Token ", apiKeySecuritySchema()))
                .info(new Info().title("Java 5 Swagger App").description("Writen by: Toichubai uulu Muhammed!"))
                .security(List.of(new SecurityRequirement().addList("Bearer token")));
    }

    private SecurityScheme apiKeySecuritySchema(){
        return new SecurityScheme()
                .name("Authorization")
                .description("put your jwt token here!")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");
    }
}
