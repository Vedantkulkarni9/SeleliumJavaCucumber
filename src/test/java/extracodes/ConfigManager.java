package extracodes;
//usoing singleton
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {

    private static volatile ConfigManager instance;
    private Properties properties;

    private ConfigManager() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
