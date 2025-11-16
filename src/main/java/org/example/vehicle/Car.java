package org.example.vehicle;

import java.util.Objects;

public class Car implements Vehicle{
    private final String id;
    private final String model;
    private final String engine;
    private final int seats;
    private final float basePricePerDay;
    private final float basePricePerHour;

    public Car(String id, String model, String engine, int seats,
               float basePricePerDay, float basePricePerHour) {
        this.id = Objects.requireNonNull(id);
        this.model = Objects.requireNonNull(model);
        this.engine = engine;
        this.seats = seats;
        this.basePricePerDay = basePricePerDay;
        this.basePricePerHour = basePricePerHour;
    }

    @Override public String getId() { return id; }
    @Override public String getType() { return "Car"; }
    @Override public String getModel() { return model; }
    @Override public float getBasePricePerDay() { return basePricePerDay; }
    @Override public float getBasePricePerHour() { return basePricePerHour; }
    @Override public String getDescription() {
        return String.format("Car[id=%s, model=%s, engine=%s, seats=%d]", id, model, engine, seats);
    }
}
