package com.ferry.zenfoodapi.api.service.api;

import com.ferry.zenfoodapi.api.service.CozinhaService;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CozinhaServiceApiTest {

    @Autowired
    private CozinhaService cozinhaService;

    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUp() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        basePath = "/cozinhas";

        flyway.migrate();
    }

    @Test
    public void listarComStatusCode200() {
        given()
                .accept("application/json")
                .when().get()
                .then()
                .statusCode(200);
    }

    @Test
    public void listarVerificandoQuatidadeDeCozinhas() {
        given()
                .accept("application/json")
                .when().get()
                .then()
                .body("content", hasSize(5))
                .body("content.nome", hasItems("Brasileira", "Japonesa"));
    }

    @Test
    public void buscar() {
        given().basePath("/cozinhas/1")
                .accept("application/json")
                .when().get()
                .then()
                .statusCode(200);
    }

    @Test
    public void salvar() {
        given()
                .body("{\"nome\": \"Africana\"}")
                .contentType("application/json")
                .accept("application/json")
                .when().post()
                .then()
                .statusCode(201)
                .body("nome", Matchers.equalTo("Africana"));
    }

    @Test
    public void atualizar() {
    }

    @Test
    public void remover() {
    }
}