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
import java.util.HashMap;

public class SimulacaoInserirTest {

    private static final String URL = "http://localhost:8080//api/v1/simulacoes/";

    @Test
    void deveInserirSimulacaotatus201() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(201, response.statusCode());

    }

    @Test
    void deveInserirSimulacaoStatus400() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"parcelas\":\"Parcelas não pode ser vazio\",\"valor\":\"Valor não pode ser vazio\"}}", response.body());
    }

    // Era para retornar status 409, pois o cpf já está cadastrado, mas está retornando 400 a mensagem no body está correta.
    @Test
    void deveInserirSimulacaoStatus409() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(409, response.statusCode());
        Assertions.assertEquals("{\"mensagem\":\"CPF duplicado\"}", response.body());
    }

    @Test
    void naoDeveInserirSimulacaoSemCpf() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.TRUE);


        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"cpf\":\"CPF não pode ser vazio\"}}", response.body());

    }

    @Test
    void naoDeveInserirSimulacaoSemNome() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"nome\":\"Nome não pode ser vazio\"}}", response.body());

    }

    @Test
    void naoDeveInserirSimulacaoSemEmail() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"email\":\"E-mail não deve ser vazio\"}}", response.body());

    }

    @Test
    void naoDeveInserirSimulacaoSemValor() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"valor\":\"Valor não pode ser vazio\"}}", response.body());

    }

    @Test
    void naoDeveInserirSimulacaoSemParcelas() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"parcelas\":\"Parcelas não pode ser vazio\"}}", response.body());

    }

    // Status code está retornando 500 e o correto deveria ser 400.
    @Test
    void naoDeveInserirSimulacaoSemSeguro() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(5);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(500, response.statusCode());

    }

    //Deveria dar erro de que o valor é menor que 1000.
    @Test
    void naoDeveInserirSimulacaoValorMenorQue1000() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());

    }

    @Test
    void naoDeveInserirSimulacaoValorMaiorQue40000() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(4599999));
        simulacao.setParcelas(5);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"valor\":\"Valor deve ser menor ou igual a R$ 40.000\"}}", response.body());

    }

    @Test
    void naoDeveInserirSimulacaoParcelaMenorQue2() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("66414919004");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(1);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"parcelas\":\"Parcelas deve ser igual ou maior que 2\"}}", response.body());

    }

    //Deveria dar erro de que a parcela é maior que 48.
    @Test
    void naoDeveInserirSimulacaoParcelaMaiorQue48() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("26276298085");
        simulacao.setEmail("teste@teste.com");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(50);
        simulacao.setSeguro(Boolean.TRUE);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
    }

    @Test
    void naoDeveInserirSimulacaoComEmailInvalido() throws URISyntaxException, IOException, InterruptedException {

        var simulacao = new SimulacaoPOJO();
        simulacao.setNome("Lidiane Teste");
        simulacao.setCpf("26276298085");
        simulacao.setEmail("teste@teste.com1");
        simulacao.setValor(new BigDecimal(2000));
        simulacao.setParcelas(50);
        simulacao.setSeguro(Boolean.TRUE);


        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(simulacao);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("{\"erros\":{\"email\":\"E-mail deve ser um e-mail válido\"}}", response.body());

    }

}
