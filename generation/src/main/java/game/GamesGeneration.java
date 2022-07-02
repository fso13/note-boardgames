package game;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Optional;

/**
 * created 14/01/2022
 *
 * @author Rudenko Dmitry
 */
public class GamesGeneration {
    public static void main(String[] args) throws IOException {
        String userDir = System.getProperties().getProperty("user.dir");

        String list = new String(Files.readAllBytes(Paths.get(userDir + "\\js\\games.js")), StandardCharsets.UTF_8).replace("var games =", "");
        list = list.trim();
        JsonReader reader = new JsonReader(new StringReader(list));
        reader.setLenient(true);

        Type listType = new TypeToken<ArrayList<GameItem>>() {
        }.getType();
        ArrayList<GameItem> games = new Gson().fromJson(reader, listType);

        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>%s</title>\n" +
                "    <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"../../ico.png\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta property=\"og:url\" content=\"https://fso13.github.io/note-boardgames/html/games/%s.html\"/>\n" +
                "    <meta property=\"og:type\" content=\"article\"/>\n" +
                "    <meta property=\"og:site_name\" content=\"Бардовский университет\"/>\n" +
                "    <meta property=\"og:title\" content=\"%s\"/>\n" +
                "    <meta property=\"og:description\" content=\"%s\"/>\n" +
                "    <meta property=\"og:image\" content=\"%s\"/>\n" +
                "    <meta property=\"og:image:alt\" content=\"%s\"/>\n" +
                "    <meta property=\"og:image:width\" content=\"550\"/>\n" +
                "    <meta property=\"og:image:height\" content=\"550\"/>\n" +
                "    <script\n" +
                "            src=\"https://code.jquery.com/jquery-3.6.0.min.js\"\n" +
                "            integrity=\"sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=\"\n" +
                "            crossorigin=\"anonymous\"></script>\n" +
                "    </script>\n" +
                "    <link href=\"https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css\" rel=\"stylesheet\">\n" +
                "    <script src=\"https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp\"></script>\n" +
                "\n" +
                "    <script src=\"../../js/note.js\"></script>\n" +
                "    <script src=\"../../js/games.js\"></script>\n" +
                "    <script src=\"../../js/modal.js\"></script>\n" +
                "    <script src=\"../../js/header.js\"></script>\n" +
                "</head>\n" +
                "<body translate=\"no\">\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "<script defer>\n" +
                "    var nav = document.createElement('div');\n" +
                "    nav.className = 'flex flex-no-wrap font-mono';\n" +
                "    nav.innerHTML = headerHtml;\n" +
                "    nav.id = 'header';\n" +
                "\n" +
                "    document.body.appendChild(nav);\n" +
                "\n" +
                "    var mainDiv = document.createElement('div');\n" +
                "\n" +
                "    mainDiv.innerHTML = " +
                "        '<div style=\"width: 100%%; height: 100%%\" class=\"p-8  transition duration-200 ease-in-out z-10  top-0 right-0 bottom-0 left-0\" id=\"modal\">\\n' +\n" +
                "        '            <div role=\"alert\" class=\"container mx-auto  flex justify-center\">\\n' +\n" +
                "        '                <div class=\"relative w-11/12 sm:w-8/12 md:w-9/12 pt-10 pb-8 rounded\">\\n' +\n" +
                "        '                    <div class=\"flex flex-col items-center px-4 md:px-12\">\\n' +\n" +
                "        '                        <p class=\"pb-10 text-base sm:text-lg md:text-2xl font-bold md:leading-6 mt-6 text-gray-800 text-center \">%s</p>\\n' +\n" + //title
                "        '                        <img class=\"object-scale-down h-80 w-80 p-3 contrast-125 hue-rotate-15\" src=\"%s\">\\n' +\n" +
                "        '                        <p class=\"text-xs sm:text-sm leading-5 mt-2 sm:mt-4 text-center text-sky-500  \">%s</p>\\n' +\n" + //игроки
                "        '                        <p class=\"focus:outline-none text-lg font-medium text-gray-800 \">%s</p>\\n' +\n" + //описание
                "        '                    <div>\\n' \n" +
                "        ;\n" +
                "    mainDiv.className = 'rounded';\n" +
                "    document.getElementById('container').className = 'container grid';\n\n" +
                "    document.getElementById('container').appendChild(mainDiv);\n" +
                "</script>\n" +
                "\n" +
                "</html>\n";


        games.forEach(game -> {

            String total = String.format(html,
                    game.title,
                    game.id,
                    game.title,
                    game.title,
                    game.photoUrl,
                    game.title,
                    game.title,
                    game.photoUrl,
                    String.format("от %s до %s игроков", game.playersMin, "0".equals(game.playersMax) ? "∞" : game.playersMax),
                    Optional.ofNullable(game.description).orElse(game.descriptionShort).replaceAll("\r\n", "<\\br>"));

            try {
                Files.write(Paths.get(userDir + "\\html\\games\\" + game.id + ".html"), total.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
