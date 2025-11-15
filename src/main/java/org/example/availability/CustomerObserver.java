package org.example.availability;

public class CustomerObserver implements Observer {
    private final String customerName;

    //constants
    public static final String VEHICLE_AVAILABLE = "AVAILABLE";
    public static final String VEHICLE_NOT_AVAILABLE = "UNAVAILABLE";
    public static final String CUSTOMER_NOTIFY = "Customer %s notified: Vehicle %s is now %s%n";

    public CustomerObserver(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(String vehicleId, boolean isAvailable) {
        String status = isAvailable ? VEHICLE_AVAILABLE : VEHICLE_NOT_AVAILABLE;
        System.out.printf(CUSTOMER_NOTIFY,
                customerName, vehicleId, status);
    }
}