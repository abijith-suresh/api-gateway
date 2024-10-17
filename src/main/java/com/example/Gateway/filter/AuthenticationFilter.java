package com.example.Gateway.filter;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;

    public AuthenticationFilter(){
        super(Config.class);
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config){
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing Authorization Header");
                }
                String authHeaderToken = exchange.getRequest().getHeaders().get(org.springframework.http.HttpHeaders.AUTHORIZATION).get(0);
                if (authHeaderToken != null && authHeaderToken.startsWith("Bearer")){
                   authHeaderToken = authHeaderToken.substring(7);
                }
                try {
                    RestClient restClient = RestClient.create();
                    restClient.get().uri("http://localhost:9095/api/auth/validate/token?token="+authHeaderToken).retrieve().body(Boolean.class);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    throw new RuntimeException("Invalid access: " + e.getMessage());
                }
            }
            return chain.filter(exchange);
        });
    }
}
