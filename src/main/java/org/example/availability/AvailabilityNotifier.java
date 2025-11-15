package org.example.availability;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityNotifier implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        if (observer == null) return;
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String vehicleId, boolean isAvailable) {
        for (Observer observer : observers) {
            observer.update(vehicleId, isAvailable);
        }
    }
}
