package org.example.vehicle.abstractvehicle;

import org.example.vehicle.Vehicle;


public abstract class VehicleDecorator implements Vehicle {

    protected final Vehicle decoratedVehicle;

    public VehicleDecorator(Vehicle decoratedVehicle) {
        this.decoratedVehicle = decoratedVehicle;
    }

    @Override
    public String getId() {
        return decoratedVehicle.getId();
    }

    @Override
    public String getType() {
        return decoratedVehicle.getType();
    }

    @Override
    public String getModel() {
        return decoratedVehicle.getModel();
    }

    @Override
    public float getBasePricePerDay() {
        return decoratedVehicle.getBasePricePerDay();
    }

    @Override
    public float getBasePricePerHour() {
        return decoratedVehicle.getBasePricePerHour();
    }

    @Override
    public String getDescription() {
        return decoratedVehicle.getDescription();
    }
}

