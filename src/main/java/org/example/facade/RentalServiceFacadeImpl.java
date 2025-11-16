package org.example.facade;

import org.example.facade.addon.AddOnService;
import org.example.facade.factory.VehicleFactoryService;
import org.example.facade.inventory.InventoryService;
import org.example.facade.inventory.InMemoryInventoryService;
import org.example.facade.notification.NotificationService;
import org.example.facade.notification.AvailabilityNotificationService;
import org.example.pricing.PricingStrategy;
import org.example.vehicle.Vehicle;

import java.util.Map;
import java.util.Objects;

public class RentalServiceFacadeImpl implements RentalServiceFacade {

    private final NotificationService notificationService;
    private final InventoryService inventoryService;
    private final VehicleFactoryService factoryService;
    private final AddOnService addOnService;

    public RentalServiceFacadeImpl() {
        this.notificationService = new AvailabilityNotificationService();
        this.inventoryService = new InMemoryInventoryService();
        this.factoryService = new VehicleFactoryService();
        this.addOnService = new AddOnService();
    }

    // Observer
    @Override
    public void addCustomer(String name) {
        notificationService.addCustomer(name);
    }

    // factory + inventory
    @Override
    public Vehicle createVehicle(String id, String model, String type) {
        Vehicle v = factoryService.createStandard(id, model, type);
        inventoryService.addVehicle(v);
        // notify observers that new vehicle is available
        notificationService.notifyAvailability(id, true);
        return v;
    }

    // Decorators (like addons, it seems like a good name guys)
    @Override
    public Vehicle addGPS(Vehicle vehicle) {
        Vehicle decorated = addOnService.addGPS(Objects.requireNonNull(vehicle));
        // update inventory record (replace)
        inventoryService.addVehicle(decorated);
        return decorated;
    }

    @Override
    public Vehicle addInsurance(Vehicle vehicle) {
        Vehicle decorated = addOnService.addInsurance(Objects.requireNonNull(vehicle));
        inventoryService.addVehicle(decorated);
        return decorated;
    }

    @Override
    public Vehicle addChildSeat(Vehicle vehicle) {
        Vehicle decorated = addOnService.addChildSeat(Objects.requireNonNull(vehicle));
        inventoryService.addVehicle(decorated);
        return decorated;
    }

    // Strategy for renting
    @Override
    public float rentVehicle(Vehicle vehicle, float duration, PricingStrategy strategy) {
        Objects.requireNonNull(vehicle);
        Objects.requireNonNull(strategy);
        float price = strategy.calculatePrice(vehicle, duration);
        // mark unavailable in observers (we keep in inventory but notify unavailable)
        notificationService.notifyAvailability(vehicle.getId(), false);
        // optionally remove from available map if we want, like, to mark taken:
        inventoryService.removeVehicle(vehicle.getId());
        return price;
    }

    @Override
    public void returnVehicle(Vehicle vehicle) {
        Objects.requireNonNull(vehicle);
        // put back into inventory
        inventoryService.addVehicle(vehicle);
        notificationService.notifyAvailability(vehicle.getId(), true);
    }

    @Override
    public Map<String, Vehicle> getAvailableVehicles() {
        return inventoryService.getAllAvailable();
    }
}

