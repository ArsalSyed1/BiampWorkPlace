package biamp.framework.biampworkplace.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class configReaderUtilities {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis =
                    new FileInputStream("src/test/resources/configs/stage.properties");
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
