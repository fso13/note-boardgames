package template;

public enum PageType {
    GAME("templates/html/game.html"),
    NOTE("templates/html/note.html"),

    ;

    private final String path;

    PageType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
