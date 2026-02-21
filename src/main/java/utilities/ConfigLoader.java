package utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new FileNotFoundException("config.properties not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String getBaseUrl() {
        return System.getProperty("base_url", properties.getProperty("base_url"));
    }

    public static String getBrowser() {
        return System.getProperty("browser", properties.getProperty("browser"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", properties.getProperty("headless")));
    }

    public static boolean isRemote() {
        return Boolean.parseBoolean(System.getProperty("remote", properties.getProperty("remote")));
    }
}