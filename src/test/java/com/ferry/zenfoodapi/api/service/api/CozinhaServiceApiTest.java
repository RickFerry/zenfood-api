package com.ferry.zenfoodapi.api.service.api;

import com.ferry.zenfoodapi.api.service.CozinhaService;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import com.ferry.zenfoodapi.domain.repository.CozinhaRepository;
import com.ferry.zenfoodapi.util.DatabaseCleaner;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ferry.zenfoodapi.util.ResourceUtils.getContentFromResource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CozinhaServiceApiTest {

    public static final int ID_INEXISTENTE = 10;
    private String cozinhaJson;
    private int quantidade;

    @Autowired
    private CozinhaService cozinhaService;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Before
    public void setUp() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        basePath = "/cozinhas";
        this.cozinhaJson = getContentFromResource("/json/cozinha-africana.json");
        necessidadesBanco();
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
                .body("content", hasSize(this.quantidade))
                .body("content.nome", hasItems("Brasileira", "Japonesa"));
    }

    @Test
    public void successBuscarPorId() {
        given()
                .pathParams("cozinhaId", 1)
                .accept("application/json")
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(200)
                .body("nome", equalTo("Brasileira"));
    }

    @Test
    public void failBuscarPorId() {
        given()
                .pathParams("cozinhaId", ID_INEXISTENTE)
                .accept("application/json")
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void salvar() {
        given()
                .body(this.cozinhaJson)
                .contentType("application/json")
                .accept("application/json")
                .when().post()
                .then()
                .statusCode(201)
                .body("nome", equalTo("Africana"));
    }

    @Test
    public void atualizar() {
    }

    @Test
    public void remover() {
    }

    private void necessidadesBanco() {
        databaseCleaner.clearTables();
        prepararDados();
        this.quantidade = (int) cozinhaRepository.count();
    }

    private void prepararDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");
        cozinhaService.salvar(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Japonesa");
        cozinhaService.salvar(cozinha2);

        Cozinha cozinha3 = new Cozinha();
        cozinha3.setNome("Tailandesa");
        cozinhaService.salvar(cozinha3);

        Cozinha cozinha4 = new Cozinha();
        cozinha4.setNome("Francesa");
        cozinhaService.salvar(cozinha4);

        Cozinha cozinha5 = new Cozinha();
        cozinha5.setNome("Italiana");
        cozinhaService.salvar(cozinha5);
    }
}