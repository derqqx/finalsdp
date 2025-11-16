package org.example.facade.factory;

import org.example.vehicle.Vehicle;
import org.example.vehicle.factory.BikeFactory;
import org.example.vehicle.factory.CarFactory;
import org.example.vehicle.factory.VanFactory;

public class VehicleFactoryService {

    public Vehicle createStandard(String id, String model, String type) {
        return switch (type.toUpperCase()) {
            case "CAR" -> CarFactory.createStandardCar(id, model);
            case "VAN" -> VanFactory.createStandardVan(id, model);
            case "BIKE" -> BikeFactory.createStandardBike(id, model);
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }
}

