package org.example.vehicle.factory;


import org.example.vehicle.Car;
import org.example.vehicle.Vehicle;

public final class CarFactory {

    private static final String DEFAULT_ENGINE = "1.6L I4";
    private static final float DEFAULT_SEATS = 5;
    private static final float PRICE_PER_DAY = 5000;
    private static final float PRICE_PER_HOUR = 650;

    private CarFactory() {}

    public static Vehicle createStandardCar(String id, String model) {
        return new Car(
                id,
                model,
                DEFAULT_ENGINE,
                DEFAULT_SEATS,
                PRICE_PER_DAY,
                PRICE_PER_HOUR
        );
    }

    public static Vehicle createCustomCar(String id, String model, String engine,
                                          float seats, float pricePerDay, float pricePerHour) {
        return new Car(id, model, engine, seats, pricePerDay, pricePerHour);
    }
}
