package org.example.pricing;
import org.example.vehicle.Vehicle;

public interface PricingStrategy {
    float calculatePrice(Vehicle vehicle, float duration);
}
