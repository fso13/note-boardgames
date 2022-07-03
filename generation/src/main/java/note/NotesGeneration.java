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
import java.util.HashMap;
import java.util.Map;

/**
 * created 14/01/2022
 *
 * @author Rudenko Dmitry
 */
public class NotesGeneration {
    public static void main(String[] args) throws IOException {
        String userDir = System.getProperties().getProperty("user.dir");
        TemplateService templateService = new TemplateService();
        String list = new String(Files.readAllBytes(Paths.get(userDir + "\\page\\js\\note.js")), StandardCharsets.UTF_8).replace("var notes = ", "");
        list = list.trim();
        JsonReader reader = new JsonReader(new StringReader(list));
        reader.setLenient(true);

        Type listType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        ArrayList<Note> notes = new Gson().fromJson(reader, listType);

        notes.forEach(note -> {

            Map<String, Object> params = new HashMap<>();
            params.put("id", note.id);
            params.put("tags", String.join(",", note.tags));
            params.put("content", note.content.replaceAll("<.*?>", ""));
            params.put("files", note.files);
            params.put("file0", note.files.get(0));
            params.put("title", note.title);
            Map<String, Object> noteMap = new HashMap<>();
            noteMap.put("note", params);
            String total = templateService.render(PageType.NOTE, noteMap);


            try {
                Files.write(Paths.get(userDir + "\\page\\html\\notes\\" + note.id + ".html"), total.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
