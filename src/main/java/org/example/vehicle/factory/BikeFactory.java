package org.example.vehicle.factory;

import org.example.vehicle.Bike;
import org.example.vehicle.Vehicle;

public final class BikeFactory {

    private static final float PRICE_PER_DAY = 1500;
    private static final float PRICE_PER_HOUR = 250;

    private BikeFactory() {}

    public static Vehicle createStandardBike(String id, String model) {
        return new Bike(
                id,
                model,
                PRICE_PER_DAY,
                PRICE_PER_HOUR
        );
    }

    public static Vehicle createCustomBike(String id, String model,
                                           float pricePerDay, float pricePerHour) {
        return new Bike(id, model, pricePerDay, pricePerHour);
    }
}
