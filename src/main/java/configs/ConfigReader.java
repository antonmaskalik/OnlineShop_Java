package configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import configs.models.ConfigModel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
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
            log.info("Loading configuration from file: " + FILE_NAME);
            return config;
        }
    }

    private static ConfigModel loadConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME)) {
            if (input == null) {
                log.error("Configuration file not found: {}", FILE_NAME);
                throw new RuntimeException("Configuration file not found: " + FILE_NAME);
            }
            log.info("Configuration successfully loaded.");
            return objectMapper.readValue(input, ConfigModel.class);
        } catch (IOException e) {
            log.error("Could not load or deserialize JSON configuration file: {} - {}", FILE_NAME, e.getMessage(), e);
            throw new RuntimeException("Could not load configuration file: " + FILE_NAME + e.getMessage(), e);
        }
    }
}
