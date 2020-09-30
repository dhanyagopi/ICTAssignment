package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {
    private static config conf = null;
    public Properties prof;
    public FileInputStream file;

    public config() throws IOException {
        prof = new Properties();
        file = new FileInputStream("C:\\Project\\ICTAssignment\\src\\main\\resources\\config.properties");
        prof.load(file);
        file.close();
    }

    public static config configReturn() throws IOException {
        return new config();
    }

    public String get(String key) {
        return prof.getProperty(key);
    }
}
