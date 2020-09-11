package lk.mad.teacollecting;

public class Driver {
    private static int driver_id;
    private static String name;
    private static int path_id;
//  private static String path;

    public Driver(int driver_id, String name, int path_id) {
        this.driver_id = driver_id;
        this.name = name;
        this.path_id = path_id;
    }

    public static String getName() {
        return name;
    }

    public static int getDriver_id() {
        return driver_id;
    }

    public static int getPath_id() {
        return path_id;
    }
}
