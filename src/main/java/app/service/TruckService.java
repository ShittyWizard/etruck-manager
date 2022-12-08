package app.service;

import app.exception.TruckNotFoundException;
import app.exception.TruckOverloadException;
import app.model.entity.box.Box;
import app.model.entity.truck.Truck;

import java.util.List;

public interface TruckService {
    Truck registerTruck(Truck truck);
    Truck addBoxesToTruck(String serialNumber, List<Box> boxes) throws TruckNotFoundException, TruckOverloadException;

    List<Box> getBoxesByTruck(String serialNumber) throws TruckNotFoundException;
    List<Truck> getAvailableTrucks();
    Short getBatteryLevelByTruck(String serialNumber) throws TruckNotFoundException;
}
