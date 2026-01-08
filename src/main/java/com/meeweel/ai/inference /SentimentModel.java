package com.meeweel.ai.inference;

import java.util.Map;
public class SentimentModel {

    private final double bias;
    private final Map<String, Double> weights;

    public SentimentModel(double bias, Map<String, Double> weights) {
        this.bias = bias;
        this.weights = weights;
    }

    public double predictProbability(Map<String, Integer> features) {
        double linear = bias;
        for (Map.Entry<String, Integer> entry : features.entrySet()) {
            Double weight = weights.get(entry.getKey());
            if (weight != null) {
                linear += weight * entry.getValue();
            }
        }
        return 1.0d / (1.0d + Math.exp(-linear));
    }
}
