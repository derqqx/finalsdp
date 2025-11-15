package org.example.vehicle.decorator;
import org.example.vehicle.Vehicle;

public class InsuranceDecorator extends VehicleDecorator {
    private static final float INSURANCE_PRICE_PER_DAY = 300f;
    private static final float INSURANCE_PRICE_PER_HOUR = 50f;

    public InsuranceDecorator(Vehicle decoratedVehicle) {
        super(decoratedVehicle);
    }

    @Override
    public float getBasePricePerDay() {
        float newPricePerDay;
        newPricePerDay = super.getBasePricePerDay() + INSURANCE_PRICE_PER_DAY;

        return newPricePerDay;
    }

    @Override
    public float getBasePricePerHour() {
        float newPricePerHour;
        newPricePerHour = super.getBasePricePerHour() + INSURANCE_PRICE_PER_HOUR;

        return newPricePerHour;
    }

    @Override
    public String getDescription() {
        String description;
        description = super.getDescription() + " + Insurance";

        return description;
    }
}

