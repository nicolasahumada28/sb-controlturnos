package com.timmynet.apigateway.apigw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.Route;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GatewayRouteDetailsTest {

    @Autowired
    private RouteLocator routeLocator;

    @Test
    void routesHaveExpectedUriAndFilters() {
        List<Route> routes = routeLocator.getRoutes().collectList().block();
        assertThat(routes).isNotNull();

        Route admin = routes.stream().filter(r -> "auth-service-admin".equals(r.getId())).findFirst().orElse(null);
        Route auth = routes.stream().filter(r -> "auth-service-auth".equals(r.getId())).findFirst().orElse(null);
        Route col = routes.stream().filter(r -> "colaborador-service".equals(r.getId())).findFirst().orElse(null);

        assertThat(admin).isNotNull();
        assertThat(auth).isNotNull();
        assertThat(col).isNotNull();

        assertThat(admin.getUri().toString()).isEqualTo("lb://auth-service");
        assertThat(auth.getUri().toString()).isEqualTo("lb://auth-service");
        assertThat(col.getUri().toString()).isEqualTo("lb://colaborador-service");

        // Verificamos que existan filtros configurados (esperamos StripPrefix)
        assertThat(admin.getFilters()).isNotEmpty();
        assertThat(auth.getFilters()).isNotEmpty();
        assertThat(col.getFilters()).isNotEmpty();
    }

}
