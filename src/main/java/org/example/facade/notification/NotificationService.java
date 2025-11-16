package org.example.facade.notification;

public interface NotificationService {
    void addCustomer(String name);
    void notifyAvailability(String vehicleId, boolean isAvailable);
}

