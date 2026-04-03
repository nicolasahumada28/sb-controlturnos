package com.timmynet.apigateway.apigw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.Route;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GatewayRoutesTest {

    @Autowired
    private RouteLocator routeLocator;

    @Test
    void routeLocatorContainsExpectedRoutes() {
        List<String> ids = routeLocator.getRoutes()
                .map(Route::getId)
                .collectList()
                .block();

        assertThat(ids).isNotNull();
        // Esperamos que existan las rutas que configuramos en application.yml
        assertThat(ids).contains("auth-service-admin", "auth-service-auth", "colaborador-service");
    }

}
