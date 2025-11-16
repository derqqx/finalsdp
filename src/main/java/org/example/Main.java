package org.example;

import org.example.facade.RentalServiceFacade;
import org.example.facade.RentalServiceFacadeImpl;
import org.example.pricing.DailyPricing;
import org.example.pricing.HourlyPricing;
import org.example.pricing.PricingStrategy;
import org.example.rental_order.RentalOrder;
import org.example.rental_order.RentalOrderBuilder;
import org.example.vehicle.Vehicle;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Vehicle Rental Service ===");

        // Create facade (Facade pattern)
        RentalServiceFacade facade = new RentalServiceFacadeImpl();

        // Observer pattern: customers who want notifications
        facade.addCustomer("Kairat Nurtas");
        facade.addCustomer("Billy Herington");

        System.out.println("\n--- Creating standard vehicles via Facade (uses VehicleFactory internally) ---");
        // Factory pattern: create vehicles via factories through facade
        Vehicle car1 = facade.createVehicle("C1", "Toyota Corolla", "car");
        Vehicle van1 = facade.createVehicle("V1", "Ford Transit", "van");
        Vehicle bike1 = facade.createVehicle("B1", "Giant Escape", "bike");

        // Decorator pattern: add addons (GPS, Insurance, Child seat)
        car1 = facade.addGPS(car1);                // car now has GPS
        car1 = facade.addInsurance(car1);          // car now has GPS + Insurance
        van1 = facade.addChildSeat(van1);          // van has child seat

        // Show available vehicles (Facade delegates to inventory service)
        System.out.println("\n--- Available vehicles after creation and decorating: ---");
        printInventory(facade.getAvailableVehicles());

        // Strategy pattern: choose pricing strategy
        PricingStrategy daily = new DailyPricing();   // price per day
        PricingStrategy hourly = new HourlyPricing(); // price per hour

        // Builder pattern: build a RentalOrder (immutable object)
        RentalOrder order = new RentalOrderBuilder()
                .setOrderId("ORDER-100")
                .setVehicle(car1)
                .setRentalHours(2.0f)   // if using HourlyPricing, treat as hours
                .setPricingStrategy(hourly)
                .includeInsurance(true)
                .build();

        System.out.println("\n--- Created order: " + order + " ---");

        // Rent the vehicle using facade (Strategy is used inside order but we also show direct rent)
        float priceViaFacade = facade.rentVehicle(car1, 2.0f, hourly);
        System.out.println("Price charged by facade for 2 hours (car1): " + priceViaFacade);

        // The order can calculate price as well (Builder + Strategy)
        System.out.println("Order.calculatePrice(): " + order.calculatePrice());

        // Inventory after renting
        System.out.println("\nAvailable vehicles after renting car1:");
        printInventory(facade.getAvailableVehicles());

        // Return vehicle -> notify observers and add back to inventory
        facade.returnVehicle(car1);

        System.out.println("\nAvailable vehicles after returning car1:");
        printInventory(facade.getAvailableVehicles());

        // Another scenario: rent van for 1 day (demonstrates daily pricing)
        RentalOrder vanOrder = new RentalOrderBuilder()
                .setOrderId("ORDER-101")
                .setVehicle(van1)
                .setRentalHours(1.0f) // 1 day in DailyPricing semantics
                .setPricingStrategy(daily)
                .includeInsurance(false)
                .build();

        System.out.println("\n--- Created Van order: " + vanOrder + " ---");
        float vanPrice = facade.rentVehicle(van1, 1.0f, daily);
        System.out.println("Price for van 1 day: " + vanPrice);

        System.out.println("\n--- Final available vehicles: ---");
        printInventory(facade.getAvailableVehicles());

        System.out.println("\n=== Program finished ===");
    }

    private static void printInventory(Map<String, Vehicle> available) {
        if (available.isEmpty()) {
            System.out.println("  (none)");
            return;
        }
        available.forEach((id, v) -> System.out.printf("  %s -> %s | desc: %s | dayPrice: %.2f | hourPrice: %.2f%n",
                id, v.getType(), v.getDescription(), v.getBasePricePerDay(), v.getBasePricePerHour()));
    }
}


