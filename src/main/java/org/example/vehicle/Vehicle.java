package org.example.vehicle;

public interface Vehicle {
    String getId();
    String getType(); // e.g., "Car", "Van", "Bike"
    String getModel();
    int getBasePricePerDay();
    int getBasePricePerHour();
    String getDescription();
}
