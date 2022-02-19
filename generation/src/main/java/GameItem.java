import java.util.List;

/**
 * created 02/02/2022
 *
 * @author Rudenko Dmitry
 */
public class GameItem {
    public String id;
    public String title;
    public String photoUrl;
    public String description;
    public String playersMin;
    public String playersMax;

    @Override
    public String toString() {
        return "GameItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", description='" + description + '\'' +
                ", playersMin='" + playersMin + '\'' +
                ", playersMax='" + playersMax + '\'' +
                '}';
    }
}
