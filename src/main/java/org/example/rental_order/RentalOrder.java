package org.example.rental_order;

import org.example.pricing.PricingStrategy;
import org.example.vehicle.Vehicle;

/**
 * BUILDER PATTERN — Immutable Rental Order object.
 */
public class RentalOrder {

    private final String orderId;
    private final Vehicle vehicle;
    private final float rentalHours;
    private final PricingStrategy pricingStrategy;
    private final boolean insuranceIncluded;

    protected RentalOrder(RentalOrderBuilder builder) {
        this.orderId = builder.orderId;
        this.vehicle = builder.vehicle;
        this.rentalHours = builder.rentalHours;
        this.pricingStrategy = builder.pricingStrategy;
        this.insuranceIncluded = builder.insuranceIncluded;
    }

    public String getOrderId() { return orderId; }
    public Vehicle getVehicle() { return vehicle; }
    public float getRentalHours() { return rentalHours; }
    public PricingStrategy getPricingStrategy() { return pricingStrategy; }
    public boolean isInsuranceIncluded() { return insuranceIncluded; }

    public float calculatePrice() {
        return pricingStrategy.calculatePrice(vehicle, rentalHours);
    }

    @Override
    public String toString() {
        return "RentalOrder{" +
                "orderId='" + orderId + '\'' +
                ", vehicle=" + vehicle.getDescription() +
                ", rentalHours=" + rentalHours +
                ", insuranceIncluded=" + insuranceIncluded +
                '}';
    }
}
