package br.com.invillia.cdb.customer.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI().info(new Info()
                .title("API para gerenciamento de clientes e negociação de CDBs")
                .description("Esta API disponibiliza recursos para gerenciamento de clientes e negociação de CDBs.")
                .version("1.0")
        );
    }

}
