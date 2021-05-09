package com.springcloud.demo.springcloudapigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRouteConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder routeBuilder) {
		
		return routeBuilder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("My Header", "Custom Header")
								.addRequestParameter("custom_param", "This is a custom param!!"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/exchange/**")
						.uri("lb://currency-exchange-service"))
				.route(p -> p.path("/convert/**")
						.uri("lb://currency-conversion-service"))
				.route(p -> p.path("/convert-feign/**")
						.uri("lb://currency-conversion-service"))
				 .route(p -> p.path("/convert-feign-new/**")
						 .filters(f -> f.rewritePath("/convert-feign-new/(?<segment>.*)", "/convert-feign/${segment}"))
						 .uri("lb://currency-conversion-service"))
				.build();
	}
}
