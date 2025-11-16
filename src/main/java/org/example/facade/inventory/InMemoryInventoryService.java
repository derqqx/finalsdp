package org.example.facade.inventory;

import org.example.vehicle.Vehicle;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryInventoryService implements InventoryService {

    private final Map<String, Vehicle> availableVehicles = new HashMap<>();

    @Override
    public void addVehicle(Vehicle vehicle) {
        Objects.requireNonNull(vehicle);
        availableVehicles.put(vehicle.getId(), vehicle);
    }

    @Override
    public Vehicle getVehicle(String id) {
        return availableVehicles.get(id);
    }

    @Override
    public Map<String, Vehicle> getAllAvailable() {
        Map<String, Vehicle> stringVehicleMap;
        stringVehicleMap = Collections.unmodifiableMap(new HashMap<>(availableVehicles));
        return stringVehicleMap;
    }

    @Override
    public void removeVehicle(String id) {
        availableVehicles.remove(id);
    }
}

