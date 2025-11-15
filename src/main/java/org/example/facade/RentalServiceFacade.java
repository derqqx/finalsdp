package org.example.facade;

import org.example.vehicle.Vehicle;
import org.example.vehicle.factory.VehicleFactory;
import org.example.vehicle.decorator.*;
import org.example.pricing.PricingStrategy;
import org.example.availability.AvailabilityNotifier;
import org.example.availability.CustomerObserver;
import java.util.HashMap;
import java.util.Map;

public class RentalServiceFacade {

    private final AvailabilityNotifier notifier = new AvailabilityNotifier();
    private final Map<String, Vehicle> availableVehicles = new HashMap<>();

    public RentalServiceFacade() {}

    public void addCustomer(String name) {
        CustomerObserver customer = new CustomerObserver(name);
        notifier.addObserver(customer);
    }

    // create vehicle via factory
    public Vehicle createVehicle(String id, String model, String type) {
        Vehicle vehicle;
        switch (type.toUpperCase()) {
            case "CAR" -> vehicle = VehicleFactory.createStandardCar(id, model);
            case "VAN" -> vehicle = VehicleFactory.createStandardVan(id, model);
            case "BIKE" -> vehicle = VehicleFactory.createStandardBike(id, model);
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        }
        availableVehicles.put(id, vehicle);
        notifier.notifyObservers(id, true);
        return vehicle;
    }

    // Here I add optional services via decorator
    public Vehicle addGPS(Vehicle vehicle) {
        return new GPSDecorator(vehicle);
    }

    public Vehicle addInsurance(Vehicle vehicle) {
        return new InsuranceDecorator(vehicle);
    }

    public Vehicle addChildSeat(Vehicle vehicle) {
        return new ChildSeatDecorator(vehicle);
    }

    // Rent a vehicle using strategy
    public float rentVehicle(Vehicle vehicle, int duration, PricingStrategy strategy) {
        float price = strategy.calculatePrice(vehicle, duration);
        String id = vehicle.getId();
        // mark unavailable
        notifier.notifyObservers(id, false);
        return price;
    }

    public void returnVehicle(Vehicle vehicle) {
        String id = vehicle.getId();
        notifier.notifyObservers(id, true);
    }

    // Show all available vehicles
    public Map<String, Vehicle> getAvailableVehicles() {
        HashMap<String, Vehicle> stringVehicleHashMap;
        stringVehicleHashMap = new HashMap<>(availableVehicles);
        return stringVehicleHashMap;
    }
}
