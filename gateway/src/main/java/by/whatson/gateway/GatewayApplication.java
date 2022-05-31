package by.whatson.gateway;

import by.whatson.util.helper.DiscoveryHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@SpringBootApplication(scanBasePackages = "by.whatson")
@Configuration
public class GatewayApplication {
    private final static String URI_IF_NO_SERVICE_FOUND="http://localhost:8888";
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, DiscoveryHelper discoveryHelper){
        return builder.routes()
                .route(p -> p.path("/user/reg")
                        .uri(discoveryHelper.serviceUrl("user-service").orElseGet(()-> URI.create(URI_IF_NO_SERVICE_FOUND))))
                .route(p -> p.path("/user/settings")
                        .uri(discoveryHelper.serviceUrl("user-service").orElseGet(()-> URI.create(URI_IF_NO_SERVICE_FOUND))))
                .route(p -> p.path("/all/get")
                        .uri(discoveryHelper.serviceUrl("news-service").orElseGet(()-> URI.create(URI_IF_NO_SERVICE_FOUND))))
                .route(p -> p.path("/all/daily")
                        .uri(discoveryHelper.serviceUrl("news-service").orElseGet(()-> URI.create(URI_IF_NO_SERVICE_FOUND))))
                .build();
    }
}
