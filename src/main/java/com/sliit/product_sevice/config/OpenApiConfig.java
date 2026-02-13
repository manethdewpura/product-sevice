package com.sliit.product_sevice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI productServiceOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Product Service API")
						.description("REST API for Product CRUD operations")
						.version("1.0"));
	}
}
