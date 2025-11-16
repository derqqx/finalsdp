package org.example.vehicle.decorator;

import org.example.vehicle.Vehicle;
import org.example.vehicle.abstractvehicle.VehicleDecorator;

// we are for children's safety!
public class ChildSeatDecorator extends VehicleDecorator {
    private static final float CHILD_SEAT_PER_DAY = 100f;
    private static final float CHILD_SEAT_PER_HOUR = 10f;

    public ChildSeatDecorator(Vehicle decoratedVehicle) {
        super(decoratedVehicle);
    }

    @Override
    public float getBasePricePerDay() {
        float newPricePerDay;
        newPricePerDay = super.getBasePricePerDay() + CHILD_SEAT_PER_DAY;

        return newPricePerDay;
    }

    @Override
    public float getBasePricePerHour() {
        float newPricePerHour;
        newPricePerHour = super.getBasePricePerHour() + CHILD_SEAT_PER_HOUR;

        return newPricePerHour;
    }

    @Override
    public String getDescription() {
        String description;
        description = super.getDescription() + " + Child Seat";

        return description;
    }
}

