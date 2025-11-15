package org.example.vehicle;

import java.util.Objects;

public class Car implements Vehicle{
    private final String id;
    private final String model;
    private final String engine;
    private final int seats;
    private final int basePricePerDay;
    private final int basePricePerHour;

    public Car(String id, String model, String engine, int seats,
               int basePricePerDay, int basePricePerHour) {
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
    @Override public int getBasePricePerDay() { return basePricePerDay; }
    @Override public int getBasePricePerHour() { return basePricePerHour; }
    @Override public String getDescription() {
        return String.format("Car[id=%s, model=%s, engine=%s, seats=%d]", id, model, engine, seats);
    }
}
