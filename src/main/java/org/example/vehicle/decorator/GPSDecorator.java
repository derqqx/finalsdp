package org.example.vehicle.decorator;
import org.example.vehicle.Vehicle;
import org.example.vehicle.abstractvehicle.VehicleDecorator;

public class GPSDecorator extends VehicleDecorator {
    private static final float GPS_PRICE_PER_DAY = 200f;
    private static final float GPS_PRICE_PER_HOUR = 30f;

    public GPSDecorator(Vehicle decoratedVehicle) {
        super(decoratedVehicle);
    }

    @Override
    public float getBasePricePerDay() {
        float newPricePerDay;
        newPricePerDay = super.getBasePricePerDay() + GPS_PRICE_PER_DAY;

        return newPricePerDay;
    }

    @Override
    public float getBasePricePerHour() {
        float newPricePerHour;
        newPricePerHour = super.getBasePricePerHour() + GPS_PRICE_PER_HOUR;

        return newPricePerHour;
    }

    @Override
    public String getDescription() {
        String description;
        description = super.getDescription() + " + GPS";

        return description;
    }
}

