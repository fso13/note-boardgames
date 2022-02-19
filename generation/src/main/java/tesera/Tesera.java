package tesera;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * created 14/01/2022
 *
 * @author Rudenko Dmitry
 */
public class Tesera {
    public static void main(String[] args) {


        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("https://api.tesera.ru/v1")
                .build();


        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        List<HttpMessageConverter<?>> httpMessageConverter = new ArrayList<>();
        httpMessageConverter.add(mappingJackson2HttpMessageConverter);
        restTemplate.setMessageConverters(httpMessageConverter);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer");

        List<Map> list1 = (List) restTemplate.exchange("/collections/base/own?Limit=50",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Object.class).getBody();


        List<Map> games = new LinkedList<>();
        list1.forEach(map -> {

            HttpHeaders headers2 = new HttpHeaders();

            headers2.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer");

            Map game = (Map) restTemplate.exchange("/games/" + ((Map<?, ?>) (map.get("game"))).get("teseraId"),
                    HttpMethod.GET,
                    new HttpEntity<>(headers2),
                    Object.class).getBody();
            games.add(game);
            System.out.println(((Map<?, ?>) game.get("game")).get("title"));
            System.out.println(((Map<?, ?>) game.get("game")).get("description"));

        });
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(games));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
