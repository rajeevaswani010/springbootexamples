package com.rjasw.demo.simple_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;

@SpringBootApplication
@Controller
@EnableConfigurationProperties (UriConfiguration.class)
public class SimpleGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
		
	    return builder.routes()
	    		.route(p -> p 
	    			.path("/get")
	    			.filters(f -> f
	    				.addRequestHeader("Hello", "World")
	    			)
	    			.uri(uriConfiguration.getHttpbin())
   	    		)
	    		.build();
	}
	
	//tag : fallback..  
	@RequestMapping("/fallback")
	public Mono<String> fallback(){
		return Mono.just("fallback");
	}
	//end: fallback	
}

//uri-configuration []
@ConfigurationProperties
class UriConfiguration {
	private String httpbin = "http://httpbin.org:80";
	
	public String getHttpbin() {
		return httpbin;
	}
	
	public void setHttpbin(String uri) {
		this.httpbin = uri;
	}
}

