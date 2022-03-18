import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimulacaoConsultarTest {

    private static final String URL = "http://localhost:8080//api/v1/simulacoes/";

    @Test
    void deveRetornarSimulacoesStatus200() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(200, response.statusCode());
    }


    @Test
    void deveRetornarSimulacaoDoCpf() throws URISyntaxException, IOException, InterruptedException {
        var url = URL.concat("17822386034");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void naoDeveRetornarSimulacaoDoCpf() throws URISyntaxException, IOException, InterruptedException {
        var url = URL.concat("02655532023");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("{\"mensagem\":\"CPF 02655532023 não encontrado\"}", response.body());
    }

}
