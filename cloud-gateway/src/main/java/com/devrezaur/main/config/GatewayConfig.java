package com.devrezaur.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.devrezaur.main.filter.JwtAuthenticationFilter;


@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("AUTH-SERVICE", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://AUTH-SERVICE"))
				.route("CREATE-USER", r -> r.path("/user/create/**").filters(f -> f.filter(filter)).uri("lb://CREATE-USER"))
				.route("READ-USER", r -> r.path("/user/read/**").filters(f -> f.filter(filter)).uri("lb://READ-USER"))
				.route("UPDATE-USER", r -> r.path("/user/update/**").filters(f -> f.filter(filter)).uri("lb://UPDATE-USER"))
				.route("DELETE-USER", r -> r.path("/user/delete/**").filters(f -> f.filter(filter)).uri("lb://DELETE-USER"))
				.build();
	}

}
