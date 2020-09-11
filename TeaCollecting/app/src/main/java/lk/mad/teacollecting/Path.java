package lk.mad.teacollecting;

public class Path {
    private int path_id;
    private String name;

    public Path(int path_id, String name) {
        this.path_id = path_id;
        this.name = name;
    }

    public int getPath_id() {
        return path_id;
    }

    public void setPath_id(int path_id) {
        this.path_id = path_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
