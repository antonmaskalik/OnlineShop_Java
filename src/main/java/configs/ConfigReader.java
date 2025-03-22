package configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import configs.models.ApiConfigModel;
import configs.models.WebDriverConfigModel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public final class ConfigReader {
    private static final ConcurrentMap<String, Object> configCache = new ConcurrentHashMap<>();

    private ConfigReader() {
    }

    public static WebDriverConfigModel getDriverConfig() {
        return getConfig("config.json", WebDriverConfigModel.class);
    }

    public static ApiConfigModel getApiConfig() {
        return getConfig("apiConfig.json", ApiConfigModel.class);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getConfig(String fileName, Class<T> clazz) {
        String cacheKey = fileName + clazz.getName();
        T config = (T) configCache.get(cacheKey);
        if (config != null) {
            return config;
        }
        config = (T) configCache.computeIfAbsent(cacheKey, key -> loadConfig(fileName, clazz));
        log.info("Loading configuration from file: {}", fileName);
        return config;
    }

    private static <T> T loadConfig(String fileName, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                log.error("Configuration file not found: {}", fileName);
                throw new RuntimeException("Configuration file not found: " + fileName);
            }
            log.info("Configuration successfully loaded.");
            return objectMapper.readValue(input, clazz);
        } catch (IOException e) {
            log.error("Could not load or deserialize JSON configuration file: {} - {}", fileName, e, e);
            throw new RuntimeException("Could not load configuration file: " + fileName, e);
        }
    }
}
