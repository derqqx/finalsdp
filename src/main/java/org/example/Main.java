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
        // --- User and Configuration Variables (now mutable local variables) ---
        String user1 = "Kairat Nurtas";
        String user2 = "Billy Herington";
        String carId = "C1";
        String carModel = "Toyota Corolla";
        String carType = "car";
        String vanId = "V1";
        String vanModel = "Ford Transit";
        String vanType = "van";
        String bikeId = "B1";
        String bikeModel = "Giant Escape";
        String bikeType = "bike";
        String orderId1 = "ORDER-100";
        String orderId2 = "ORDER-101";

        // --- Output Variables (now mutable local variables) ---
        String appTitle = "Vehicle Rental Service\n";
        String creatingVehicles = "\n--- Creating standard vehicles via Facade (uses VehicleFactory internally) ---";
        String availableVehicles = "\n--- Available vehicles after creation and decorating: ---";
        String createdOrderPrefix = "\n--- Created order: ";
        String dashesSuffix = " ---";
        String priceFacadePrefix = "Price charged by facade for 2 hours (car1): ";
        String priceOrderPrefix = "Order.calculatePrice(): ";
        String availableAfterRent = "\nAvailable vehicles after renting car1:";
        String availableAfterReturn = "\nAvailable vehicles after returning car1:";
        String createdVanOrderPrefix = "\n--- Created Van order: ";
        String vanPricePrefix = "Price for van 1 day: ";
        String finalAvailableVehicles = "\n--- Final available vehicles: ---";
        String programFinished = "\n=== Program finished ===";


        System.out.println(appTitle);

        // Create facade (Facade pattern)
        RentalServiceFacade facade = new RentalServiceFacadeImpl();

        // Observer pattern: customers who want notifications
        facade.addCustomer(user1);
        facade.addCustomer(user2);

        System.out.println(creatingVehicles);
        // Factory pattern: create vehicles via factories through facade
        Vehicle car1 = facade.createVehicle(carId, carModel, carType);
        Vehicle van1 = facade.createVehicle(vanId, vanModel, vanType);
        Vehicle bike1 = facade.createVehicle(bikeId, bikeModel, bikeType);

        // Decorator pattern: add addons (GPS, Insurance, Child seat)
        car1 = facade.addGPS(car1);                // car now has GPS
        car1 = facade.addInsurance(car1);          // car now has GPS + Insurance
        van1 = facade.addChildSeat(van1);          // van has child seat

        // Show available vehicles (Facade delegates to inventory service)
        System.out.println(availableVehicles);
        printInventory(facade.getAvailableVehicles());

        // Strategy pattern: choose pricing strategy
        PricingStrategy daily = new DailyPricing();   // price per day
        PricingStrategy hourly = new HourlyPricing(); // price per hour

        // Builder pattern: build a RentalOrder (immutable object)
        RentalOrder order = new RentalOrderBuilder()
                .setOrderId(orderId1)
                .setVehicle(car1)
                .setRentalHours(2.0f)   // if using HourlyPricing, treat as hours
                .setPricingStrategy(hourly)
                .includeInsurance(true)
                .build();

        System.out.println(createdOrderPrefix + order + dashesSuffix);

        // Rent the vehicle using facade (Strategy is used inside order but we also show direct rent)
        float priceViaFacade = facade.rentVehicle(car1, 2.0f, hourly);
        System.out.println(priceFacadePrefix + priceViaFacade);

        // The order can calculate price as well (Builder + Strategy)
        System.out.println(priceOrderPrefix + order.calculatePrice());

        // Inventory after renting
        System.out.println(availableAfterRent);
        printInventory(facade.getAvailableVehicles());

        // Return vehicle -> notify observers and add back to inventory
        facade.returnVehicle(car1);

        System.out.println(availableAfterReturn);
        printInventory(facade.getAvailableVehicles());

        // Another scenario: rent van for 1 day (demonstrates daily pricing)
        RentalOrder vanOrder = new RentalOrderBuilder()
                .setOrderId(orderId2)
                .setVehicle(van1)
                .setRentalHours(1.0f) // 1 day in DailyPricing semantics
                .setPricingStrategy(daily)
                .includeInsurance(false)
                .build();

        System.out.println(createdVanOrderPrefix + vanOrder + dashesSuffix);
        float vanPrice = facade.rentVehicle(van1, 1.0f, daily);
        System.out.println(vanPricePrefix + vanPrice);

        System.out.println(finalAvailableVehicles);
        printInventory(facade.getAvailableVehicles());

        System.out.println(programFinished);
    }

    private static void printInventory(Map<String, Vehicle> available) {
        // Local variables for print formatting
        String noneAvailable = "  (none)";
        String inventoryFormat = "  %s -> %s | desc: %s | dayPrice: %.2f | hourPrice: %.2f%n";

        if (available.isEmpty()) {
            System.out.println(noneAvailable);
            return;
        }
        available.forEach((id, v) -> System.out.printf(inventoryFormat,
                id, v.getType(), v.getDescription(), v.getBasePricePerDay(), v.getBasePricePerHour()));
    }
}