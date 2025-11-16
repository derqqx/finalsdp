package org.example.facade.inventory;

import org.example.vehicle.Vehicle;

import java.util.Map;

public interface InventoryService {
    void addVehicle(Vehicle vehicle);
    Vehicle getVehicle(String id);
    Map<String, Vehicle> getAllAvailable();
    void removeVehicle(String id);
}

