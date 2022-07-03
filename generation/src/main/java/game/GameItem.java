package game;

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
    public String descriptionShort;
    public String playersMin;
    public String playersMax;
    public String players;
    public String descriptions;

    @Override
    public String toString() {
        return "game.GameItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", description='" + description + '\'' +
                ", descriptionShort='" + descriptionShort + '\'' +
                ", playersMin='" + playersMin + '\'' +
                ", playersMax='" + playersMax + '\'' +
                '}';
    }
}
