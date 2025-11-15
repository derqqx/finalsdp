package org.example.pricing;
import org.example.vehicle.Vehicle;

public class HourlyPricing implements PricingStrategy {

    @Override
    public float calculatePrice(Vehicle vehicle, int hours) {
        if (hours <= 0) return 0;

        return vehicle.getBasePricePerHour() * hours;
    }
}
