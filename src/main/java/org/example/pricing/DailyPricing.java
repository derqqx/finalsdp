package org.example.pricing;
import org.example.vehicle.Vehicle;

public class DailyPricing implements PricingStrategy {

    @Override
    public float calculatePrice(Vehicle vehicle, int days) {
        if (days <= 0) return 0;

        return vehicle.getBasePricePerDay() * days;
    }
}
