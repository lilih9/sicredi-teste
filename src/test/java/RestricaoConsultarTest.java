import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestricaoConsultarTest {

    private static final String URL = "http://localhost:8080/api/v1/restricoes/";

    @Test
    void deveRetornarCpfComRestricao() throws URISyntaxException, IOException, InterruptedException {
        var url = URL.concat("01317496094");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("{\"mensagem\":\"O CPF 01317496094 tem problema\"}", response.body());
    }

    @Test
    void deveRetornarCpfSemRestricao() throws URISyntaxException, IOException, InterruptedException {
        var url = URL.concat("11111111111");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(204, response.statusCode());
    }
}
