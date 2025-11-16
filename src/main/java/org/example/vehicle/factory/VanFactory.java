package org.example.vehicle.factory;

import org.example.vehicle.Van;
import org.example.vehicle.Vehicle;

public final class VanFactory {

    private static final float DEFAULT_CAPACITY = 8;
    private static final float PRICE_PER_DAY = 8000;
    private static final float PRICE_PER_HOUR = 1000;

    private VanFactory() {}

    public static Vehicle createStandardVan(String id, String model) {
        return new Van(
                id,
                model,
                DEFAULT_CAPACITY,
                PRICE_PER_DAY,
                PRICE_PER_HOUR
        );
    }

    public static Vehicle createCustomVan(String id, String model, float capacity,
                                          float pricePerDay, float pricePerHour) {
        return new Van(id, model, capacity, pricePerDay, pricePerHour);
    }
}
