package com.springcloud.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	
	 public static final Contact DEFAULT_CONTACT = new Contact("Abhishek Singh", "https://www.springclouddemo.com", "scd@gmail.com");
	
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Currency-Exchange-Service Using Spring Cloud", 
				"This is a MS created using Spring Cloud to fetch the exchange rate for Currency Exchange.",
				"1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
		      new ArrayList<>());

	private static final Set<String> DEFAULT_PRODUCES_COMSUMES = new HashSet<String>(Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_COMSUMES)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

}
