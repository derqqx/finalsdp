package org.example.vehicle;

import java.util.Objects;

public class Bike implements   Vehicle {
    private final String id;
    private final String model;
    private final float basePricePerDay;
    private final float basePricePerHour;

    public Bike(String id, String model, float basePricePerDay, float basePricePerHour) {
        this.id = Objects.requireNonNull(id);
        this.model = Objects.requireNonNull(model);
        this.basePricePerDay = basePricePerDay;
        this.basePricePerHour = basePricePerHour;
    }

    @Override public String getId() { return id; }
    @Override public String getType() { return "Bike"; }
    @Override public String getModel() { return model; }
    @Override public float getBasePricePerDay() { return basePricePerDay; }
    @Override public float getBasePricePerHour() { return basePricePerHour; }
    @Override public String getDescription() {
        return String.format("Bike[id=%s, model=%s]", id, model);
    }
}
