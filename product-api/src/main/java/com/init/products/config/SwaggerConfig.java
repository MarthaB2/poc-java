package com.init.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //para que sepa que es un clase de configuracion
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.init.products.rest")) 
			.paths(PathSelectors.any())
			.build();
		
	}

}

/*
 .select()//inicializa el bilder
				.apis(RequestHandlerSelectors.basePackage("com.init.products.rest")) //que clase va a autodocumentar
				.paths(PathSelectors.any())
				.build();
				*/
