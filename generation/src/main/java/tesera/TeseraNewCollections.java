package tesera;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.GamesGeneration;
import note.NotesGeneration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * created 14/01/2022
 *
 * @author Rudenko Dmitry
 */
public class TeseraNewCollections {
    public static void main(String[] args) throws IOException {

        String userDir = System.getProperties().getProperty("user.dir");
        String login = System.getenv("TESSA_LOGIN");
        String password = System.getenv("TESSA_PASSWORD");

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("https://api.tesera.ru/v1")
                .build();

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        List<HttpMessageConverter<?>> httpMessageConverter = new ArrayList<>();
        httpMessageConverter.add(mappingJackson2HttpMessageConverter);
        restTemplate.setMessageConverters(httpMessageConverter);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json-patch+json")));

        Map<String, String> auth = (Map<String, String>) restTemplate.exchange("/auth/login",
                HttpMethod.POST,
                new HttpEntity<>(new TeseraAuth(login, password), headers),
                Object.class).getBody();

        String token = auth.get("token");

        headers.clear();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);

        List<Map> allGamesBasedCollection = (List) restTemplate.exchange("/collections/custom/2598/gamesclear?Limit=100",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Object.class).getBody();


        List<Map> games = new LinkedList<>();
        allGamesBasedCollection.forEach(map -> {

            HttpHeaders headers2 = new HttpHeaders();

            headers2.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer" + token);

            Map game = (Map) restTemplate.exchange("/games/" + ((Map<?, ?>) (map.get("game"))).get("teseraId"),
                    HttpMethod.GET,
                    new HttpEntity<>(headers2),
                    Object.class).getBody();
            Map gameClear = ((Map<?, ?>) (game.get("game")));

            games.add(gameClear);
        });
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            games.sort((o1, o2) -> {
                String d1 = (String) o1.get("title");
                String d2 = (String) o2.get("title");
                return d1.compareTo(d2);
            });
            Files.write(Paths.get(userDir + "\\page\\js\\games.js"), ("var games =" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(games)).getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            e.printStackTrace();
        }
        GamesGeneration.main(null);
        NotesGeneration.main(null);
    }
}
