package org.example.vehicle;

import java.util.Objects;

public class Van implements Vehicle{
    private final String id;
    private final String model;
    private final int capacity; // e.g., cargo or passengers
    private final float basePricePerDay;
    private final float basePricePerHour;

    public Van(String id, String model, int capacity,
               float basePricePerDay, float basePricePerHour) {
        this.id = Objects.requireNonNull(id);
        this.model = Objects.requireNonNull(model);
        this.capacity = capacity;
        this.basePricePerDay = basePricePerDay;
        this.basePricePerHour = basePricePerHour;
    }

    @Override public String getId() { return id; }
    @Override public String getType() { return "Van"; }
    @Override public String getModel() { return model; }
    @Override public float getBasePricePerDay() { return basePricePerDay; }
    @Override public float getBasePricePerHour() { return basePricePerHour; }
    @Override public String getDescription() {
        return String.format("Van[id=%s, model=%s, capacity=%d]", id, model, capacity);
    }
}
