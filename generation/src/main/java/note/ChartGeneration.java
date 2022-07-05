package note;

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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created 14/01/2022
 *
 * @author Rudenko Dmitry
 */
public class ChartGeneration {
    public static void main(String[] args) throws IOException {
        String userDir = System.getProperties().getProperty("user.dir");
        TemplateService templateService = new TemplateService();
        String list = new String(Files.readAllBytes(Paths.get(userDir + "\\js\\note.js")), StandardCharsets.UTF_8).replace("var notes = ", "");
        list = list.trim();
        JsonReader reader = new JsonReader(new StringReader(list));
        reader.setLenient(true);

        Type listType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        ArrayList<Note> notes = new Gson().fromJson(reader, listType);

        Map<String, Long> tags = notes
                .stream()
                .flatMap((Function<Note, Stream<String>>) note -> note.tags.stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Map<String, Object> params = new HashMap<>();
        params.put("labelsBarChart", tags.keySet().stream().map(s -> "\"" + s + "\"").collect(Collectors.toList()));
        params.put("data", tags.values());
        String total = templateService.render(PageType.CHART, params);
        try {
            Files.write(Paths.get(userDir + "\\chart.html"), total.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
