import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimulacaoAtualizarTest {

    private static final String URL = "http://localhost:8080//api/v1/simulacoes/";

    //Neste cenario foi atualizado todas as informações e todas estão funcionando corretamente.
    @Test
    void deveAtualizarSimulacaoComCpf() throws URISyntaxException, IOException, InterruptedException {

        var url = URL.concat("62648716050");

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("62648716050");
        simulacao.setEmail("teste1@teste.com");
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.FALSE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var simulacaoAtualizada = objectMapper.readValue(response.body(), SimulacaoPOJO.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(simulacao.getNome(), simulacaoAtualizada.getNome());
        Assertions.assertEquals(simulacao.getCpf(), simulacaoAtualizada.getCpf());
        Assertions.assertEquals(simulacao.getEmail(), simulacaoAtualizada.getEmail());
        Assertions.assertEquals(simulacao.getParcelas(), simulacaoAtualizada.getParcelas());
        Assertions.assertEquals(simulacao.getSeguro(), simulacaoAtualizada.getSeguro());
    }

    //Devia atualizar valor da simulação mais não esta sendo atualizado
    @Test
    void deveAtualizarSimulacaoCampoValor() throws URISyntaxException, IOException, InterruptedException {

        var url = URL.concat("62648716050");

        var simulacao = new SimulacaoPOJO();
        simulacao.setValor(new BigDecimal(544554));

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var simulacaoAtualizada = objectMapper.readValue(response.body(), SimulacaoPOJO.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(simulacao.getValor(), simulacaoAtualizada.getValor());

    }

    @Test
    void deveAtualizarSimulacaoSemCpf() throws URISyntaxException, IOException, InterruptedException {

        var url = URL.concat("555555555555");

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("{\"mensagem\":\"CPF 555555555555 não encontrado\"}", response.body());

    }
}
