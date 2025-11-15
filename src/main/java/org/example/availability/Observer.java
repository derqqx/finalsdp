package org.example.availability;

public interface Observer {
    void update(String vehicleId, boolean isAvailable);
}
