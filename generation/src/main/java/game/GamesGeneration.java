package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import template.PageType;
import template.TemplateService;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * created 14/01/2022
 *
 * @author Rudenko Dmitry
 */
public class GamesGeneration {
    public static void main(String[] args) throws IOException {
        String userDir = System.getProperties().getProperty("user.dir");

        String list = new String(Files.readAllBytes(Paths.get(userDir + "\\page\\js\\games.js")), StandardCharsets.UTF_8).replace("var games =", "");
        list = list.trim();
        JsonReader reader = new JsonReader(new StringReader(list));
        reader.setLenient(true);

        Type listType = new TypeToken<ArrayList<GameItem>>() {
        }.getType();
        ArrayList<GameItem> games = new Gson().fromJson(reader, listType);

        TemplateService templateService = new TemplateService();


        games.forEach(game -> {
            game.descriptions = Optional.ofNullable(game.description).orElse(game.descriptionShort).replaceAll("\r\n", "<\\br>");
            game.players = String.format("от %s до %s игроков", game.playersMin, "0".equals(game.playersMax) ? "∞" : game.playersMax);
            String total = templateService.render(PageType.GAME, Collections.singletonMap("game", new ObjectMapper().convertValue(game, Map.class)));

            try {
                Files.write(Paths.get(userDir + "\\page\\html\\games\\" + game.id + ".html"), total.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
