package org.example.facade.notification;

import org.example.availability.AvailabilityNotifier;
import org.example.availability.CustomerObserver;

public class AvailabilityNotificationService implements NotificationService {

    private final AvailabilityNotifier notifier = new AvailabilityNotifier();

    @Override
    public void addCustomer(String name) {
        if (name == null) return;
        notifier.addObserver(new CustomerObserver(name));
    }

    @Override
    public void notifyAvailability(String vehicleId, boolean isAvailable) {
        notifier.notifyObservers(vehicleId, isAvailable);
    }
}

