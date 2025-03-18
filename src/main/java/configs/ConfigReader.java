package configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import configs.models.ConfigModel;

import java.io.IOException;
import java.io.InputStream;

public final class ConfigReader {
    private static final String FILE_NAME = "config.json";
    private static volatile ConfigModel config;

    private ConfigReader() {
    }

    public static ConfigModel getDriverConfig() {
        if (config != null) {
            return config;
        }
        synchronized (ConfigReader.class) {
            if (config == null) {
                config = loadConfig();
            }
            return config;
        }
    }

    private static ConfigModel loadConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + FILE_NAME);
            }
            return objectMapper.readValue(input, ConfigModel.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not load configuration file: " + FILE_NAME + e.getMessage(), e);
        }
    }
}
