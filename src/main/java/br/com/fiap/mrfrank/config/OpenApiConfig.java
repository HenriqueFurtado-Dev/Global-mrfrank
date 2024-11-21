package br.com.fiap.mrfrank.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Usuários e Relatórios")
                        .description("MrFrank - Documentação da API Java - para gerenciamento de usuários e relatórios.")
                        .version("v0.0.1")
                        .contact(new Contact().name("Carlos henrique | RM: 553597").email("rm553597@fiap.com.br"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
