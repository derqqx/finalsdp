package org.example.facade.addon;

import org.example.vehicle.Vehicle;
import org.example.vehicle.decorator.ChildSeatDecorator;
import org.example.vehicle.decorator.GPSDecorator;
import org.example.vehicle.decorator.InsuranceDecorator;

public class AddOnService {

    public Vehicle addGPS(Vehicle vehicle) {
        return new GPSDecorator(vehicle);
    }

    public Vehicle addInsurance(Vehicle vehicle) {
        return new InsuranceDecorator(vehicle);
    }

    public Vehicle addChildSeat(Vehicle vehicle) {
        return new ChildSeatDecorator(vehicle);
    }
}

