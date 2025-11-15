package org.example.vehicle.factory;

import org.example.vehicle.*;
public final class VehicleFactory {

    // Car constants
    private static final String DEFAULT_CAR_ENGINE = "1.6L I4";
    private static final int DEFAULT_CAR_SEATS = 5;
    private static final int CAR_PRICE_PER_DAY = 5000;
    private static final int CAR_PRICE_PER_HOUR = 650;

    // Bike constants
    private static final int BIKE_PRICE_PER_DAY = 1500;
    private static final int BIKE_PRICE_PER_HOUR = 250;

    // Van constants
    private static final int DEFAULT_VAN_CAPACITY = 8;
    private static final int VAN_PRICE_PER_DAY = 8000;
    private static final int VAN_PRICE_PER_HOUR = 1000;

    private VehicleFactory() {}

    public static Vehicle createStandardCar(String id, String model) {
        return new Car(
                id,
                model,
                DEFAULT_CAR_ENGINE,
                DEFAULT_CAR_SEATS,
                CAR_PRICE_PER_DAY,
                CAR_PRICE_PER_HOUR
        );
    }

    public static Vehicle createStandardBike(String id, String model) {
        return new Bike(
                id,
                model,
                BIKE_PRICE_PER_DAY,
                BIKE_PRICE_PER_HOUR
        );
    }

    public static Vehicle createStandardVan(String id, String model) {
        return new Van(
                id,
                model,
                DEFAULT_VAN_CAPACITY,
                VAN_PRICE_PER_DAY,
                VAN_PRICE_PER_HOUR
        );
    }


    public static Vehicle createCustomCar(String id, String model, String engine,
                                          int seats, int pricePerDay, int pricePerHour) {
        return new Car(id, model, engine, seats, pricePerDay, pricePerHour);
    }

    public static Vehicle createCustomVan(String id, String model, int capacity,
                                          int pricePerDay, int pricePerHour) {
        return new Van(id, model, capacity, pricePerDay, pricePerHour);
    }

    public static Vehicle createCustomBike(String id, String model,
                                           int pricePerDay, int pricePerHour) {
        return new Bike(id, model, pricePerDay, pricePerHour);
    }
}