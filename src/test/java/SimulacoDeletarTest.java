import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimulacoDeletarTest {

    private static final String URL = "http://localhost:8080//api/v1/simulacoes/";

    //Esta retornando status 200 independente do id passado, o correto conforme a documentação era aparecer status
    // 204 e ser removida a simulação.
    @Test
    void deveDeletarSimulacaoComId() throws URISyntaxException, IOException, InterruptedException {
        var simulacaoId = "11";
        var url = URL.concat(simulacaoId);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .DELETE()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(204, response.statusCode());
    }

    //Esta retornando status 200 com id que não existe e não esta aparecendo a mensagem que era para ser mostrada.
    @Test
    void naoDeveDeletarSimulacaoComId() throws URISyntaxException, IOException, InterruptedException {
        var simulacaoId = "9778";
        var url = URL.concat(simulacaoId);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .DELETE()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(404, response.statusCode());
    }

}
