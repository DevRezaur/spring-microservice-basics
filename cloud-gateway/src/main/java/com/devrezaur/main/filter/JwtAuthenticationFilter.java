package com.devrezaur.main.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.devrezaur.main.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		final List<String> openApiEndpoints = List.of("/auth/register", "/auth/authenticate", "/auth/refreshtoken");

		Predicate<ServerHttpRequest> isApiSecured = r -> openApiEndpoints.stream()
				.noneMatch(uri -> r.getURI().getPath().contains(uri));

		if (isApiSecured.test(request)) {
			if (!request.getHeaders().containsKey("Authorization")) {
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			}

			String token = request.getHeaders().getOrEmpty("Authorization").get(0);
			if(token.equals("")) {
				response.setStatusCode(HttpStatus.BAD_REQUEST);
				return response.setComplete();
			}
				
			token = token.substring(7, token.length());

			try {
				jwtUtil.validateJwtToken(token);
			} catch(ExpiredJwtException ex) {
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			} catch (Exception ex) {
				response.setStatusCode(HttpStatus.BAD_REQUEST);
				return response.setComplete();
			}
			
			Claims claims = jwtUtil.getClaims(token);
			@SuppressWarnings("unchecked")
			List<String> roles = (List<String>) claims.get("ROLES");
			
			if(!roles.contains("ROLE_USER")) {
				response.setStatusCode(HttpStatus.BAD_REQUEST);
				return response.setComplete();
			}

			//exchange.getRequest().mutate().header("role", String.valueOf(claims.get("roles"))).build();
		}

		return chain.filter(exchange);
	}

}
