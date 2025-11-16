package org.example;



import org.example.vehicle.Vehicle;

import org.example.vehicle.decorator.ChildSeatDecorator;
import org.example.vehicle.decorator.GPSDecorator;
import org.example.vehicle.decorator.InsuranceDecorator;
import org.example.facade.RentalServiceFacade;
import org.example.pricing.DailyPricing;
import org.example.pricing.HourlyPricing;
import org.example.pricing.PricingStrategy;
import org.example.vehicle.factory.CarFactory;

import java.util.Map;

/**
 * Main demo: shows integration of patterns:
 * - Factory (VehicleFactory)
 * - Decorator (GPS, Insurance, ChildSeat)
 * - Strategy (Daily, Hourly)
 * - Observer (via facade notifications)
 * - Facade (RentalServiceFacade)
 *
 * Assumes package structure:
 * org.example.vehicle...
 * org.example.vehicle.decorator...
 * org.example.pricing...
 * org.example.availability...
 * org.example.facade...
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Vehicle Rental Service Demo ===");

        // 1) Create facade (it internally keeps AvailabilityNotifier and inventory)
        RentalServiceFacade facade = new RentalServiceFacade();

        // 2) Register two customers (they will be observers)
        facade.addCustomer("Alice");
        facade.addCustomer("Bob");

        System.out.println("\n--- Creating standard vehicles via Facade (uses VehicleFactory internally) ---");
        Vehicle v1 = facade.createVehicle("VAN-200", "Ford Transit", "VAN");
        Vehicle c1 = facade.createVehicle("CAR-100", "Toyota Corolla", "CAR");
        Vehicle b1 = facade.createVehicle("BIKE-300", "Honda CB", "BIKE");

        // Print available vehicles
        System.out.println("\nAvailable vehicles (after creation):");
        printAvailable(facade);

        // 3) Demonstrate creating a custom vehicle via factory (custom params)
        System.out.println("\n--- Creating a custom car via VehicleFactory directly ---");
        Vehicle customCar = CarFactory.createCustomCar("CAR-CUST-1", "Mazda CX-5", "2.0L Turbo", 5, 6500, 800);
        System.out.println("Custom car created: " + customCar.getDescription() +
                " | day=" + customCar.getBasePricePerDay() +
                " | hour=" + customCar.getBasePricePerHour());

        // Note: facade.createVehicle adds to its internal inventory.
        // For demo we will rent the custom car directly through the facade.rentVehicle (which triggers observers).
        System.out.println("\n--- Decorating custom car with GPS and Insurance ---");
        Vehicle customWithGps = facade.addGPS(customCar);
        Vehicle customWithGpsAndIns = facade.addInsurance(customWithGps);

        System.out.println("Decorated custom car desc: " + customWithGpsAndIns.getDescription()
                + " | day=" + customWithGpsAndIns.getBasePricePerDay()
                + " | hour=" + customWithGpsAndIns.getBasePricePerHour());

        // 4) Calculate price using Daily strategy for 3 days
        PricingStrategy daily = new DailyPricing();
        int days = 3;
        System.out.println("\n--- Pricing: daily strategy for " + days + " days ---");
        float dailyPrice = daily.calculatePrice(customWithGpsAndIns, days);
        System.out.printf("Daily price for %s for %d days = %.2f%n",
                customWithGpsAndIns.getId(), days, dailyPrice);

        // 5) Rent a standard van from facade for 2 days using DailyPricing
        System.out.println("\n--- Rent a VAN from facade (VAN-200) for 2 days ---");
        Vehicle van = facade.getAvailableVehicles().get("VAN-200");
        if (van != null) {
            // add child seat via facade decorator helper
            Vehicle vanWithChildSeat = facade.addChildSeat(van);

            PricingStrategy pricing = new DailyPricing();
            int rentDays = 2;
            float priceForVan = facade.rentVehicle(vanWithChildSeat, rentDays, pricing);
            System.out.printf("Rented %s for %d days. Price charged: %.2f%n",
                    vanWithChildSeat.getId(), rentDays, priceForVan);
        } else {
            System.out.println("VAN-200 not found in facade inventory");
        }

        // 6) Return the van (observer will be notified)
        System.out.println("\n--- Returning VAN-200 ---");
        if (van != null) {
            facade.returnVehicle(van);
        }

        // 7) Rent the bike for 5 hours using HourlyPricing
        System.out.println("\n--- Rent a BIKE (BIKE-300) for 5 hours using hourly strategy ---");
        Vehicle bike = facade.getAvailableVehicles().get("BIKE-300");
        if (bike != null) {
            PricingStrategy hourly = new HourlyPricing();
            int hours = 5;
            float bikePrice = facade.rentVehicle(bike, hours, hourly);
            System.out.printf("Rented %s for %d hours. Price charged: %.2f%n",
                    bike.getId(), hours, bikePrice);

            System.out.println("\n--- Returning BIKE-300 ---");
            facade.returnVehicle(bike);
        } else {
            System.out.println("BIKE-300 not found in facade inventory");
        }

        System.out.println("\n--- Final available vehicles snapshot ---");
        printAvailable(facade);

        System.out.println("\n=== Demo finished ===");
    }

    private static void printAvailable(RentalServiceFacade facade) {
        Map<String, Vehicle> available = facade.getAvailableVehicles();
        if (available.isEmpty()) {
            System.out.println("(no vehicles)");
            return;
        }
        for (Vehicle v : available.values()) {
            System.out.printf(" - %s | %s | day=%d | hour=%d | desc=%s%n",
                    v.getId(), v.getType(), v.getBasePricePerDay(), v.getBasePricePerHour(), v.getDescription());
        }
    }
}

