package org.example.rental_order;

import org.example.pricing.PricingStrategy;
import org.example.vehicle.Vehicle;

import java.util.Objects;

/**
 * BUILDER PATTERN — Fluent Builder for RentalOrder.
 */
public class RentalOrderBuilder {

    protected String orderId;
    protected Vehicle vehicle;
    protected float rentalHours;
    protected PricingStrategy pricingStrategy;
    protected boolean insuranceIncluded;

    public RentalOrderBuilder setOrderId(String orderId) {
        this.orderId = Objects.requireNonNull(orderId);
        return this;
    }

    public RentalOrderBuilder setVehicle(Vehicle vehicle) {
        this.vehicle = Objects.requireNonNull(vehicle);
        return this;
    }

    public RentalOrderBuilder setRentalHours(float rentalHours) {
        if (rentalHours <= 0f)
            throw new IllegalArgumentException("Rental hours must be greater than 0");

        this.rentalHours = rentalHours;
        return this;
    }

    public RentalOrderBuilder setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy);
        return this;
    }

    public RentalOrderBuilder includeInsurance(boolean include) {
        this.insuranceIncluded = include;
        return this;
    }

    public RentalOrder build() {

        if (orderId == null)
            throw new IllegalStateException("Order ID must be set");

        if (vehicle == null)
            throw new IllegalStateException("Vehicle must be set");

        if (pricingStrategy == null)
            throw new IllegalStateException("Pricing Strategy must be set");

        if (rentalHours <= 0f)
            throw new IllegalStateException("Rental hours must be greater than 0");

        return new RentalOrder(this);
    }
}
