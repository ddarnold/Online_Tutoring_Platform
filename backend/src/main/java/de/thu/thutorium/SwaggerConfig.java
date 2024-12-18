package de.thu.thutorium;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 * <p>
 * This class configures the OpenAPI documentation for the application,
 * including
 * API information and security schemes.
 * </p>
 */
@Configuration
public class SwaggerConfig {
        /**
         * Configures the custom OpenAPI documentation for the application.
         * <p>
         * This method sets up the API information, such as title, version, and
         * description,
         * and configures the security scheme for Bearer authentication using JWT.
         * </p>
         *
         * @return the configured {@link OpenAPI} object
         */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Endpoints")
                        .version("1.0")
                        .description("APIs available for the end-user"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
