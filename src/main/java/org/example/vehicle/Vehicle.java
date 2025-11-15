package org.example.vehicle;

public interface Vehicle {
    String getId();
    String getType(); // e.g., "Car", "Van", "Bike"
    String getModel();
    float getBasePricePerDay();
    float getBasePricePerHour();
    String getDescription();
}
