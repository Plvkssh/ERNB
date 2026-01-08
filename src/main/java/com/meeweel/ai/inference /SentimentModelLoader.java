package com.meeweel.ai.inference;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
public final class SentimentModelLoader {

    private SentimentModelLoader() {
    }

    public static SentimentModel load(ObjectMapper mapper, String resourcePath) {
        try (InputStream inputStream = SentimentModelLoader.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
            throw new IllegalStateException("Не найден ресурс модели: " + resourcePath);
        }
        ModelDefinition definition = mapper.readValue(inputStream, ModelDefinition.class);
        return new SentimentModel(definition.bias(), definition.weights());
        } catch (IOException e) {
            throw new IllegalStateException("Не удалось прочитать модель тональности", e);
        }
    }

    private record ModelDefinition(double bias, Map<String, Double> weights) {
    }
}
