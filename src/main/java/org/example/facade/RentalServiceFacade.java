package org.example.facade;

import org.example.pricing.PricingStrategy;
import org.example.vehicle.Vehicle;

import java.util.Map;

public interface RentalServiceFacade {
    void addCustomer(String name);
    Vehicle createVehicle(String id, String model, String type);
    Vehicle addGPS(Vehicle vehicle);
    Vehicle addInsurance(Vehicle vehicle);
    Vehicle addChildSeat(Vehicle vehicle);
    float rentVehicle(Vehicle vehicle, float duration, PricingStrategy strategy);
    void returnVehicle(Vehicle vehicle);
    Map<String, Vehicle> getAvailableVehicles();
}

