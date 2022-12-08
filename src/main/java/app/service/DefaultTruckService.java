package app.service;

import app.exception.TruckNotFoundException;
import app.exception.TruckOverloadException;
import app.model.entity.box.Box;
import app.model.entity.truck.Truck;
import app.model.entity.truck.TruckState;
import app.repository.BoxRepository;
import app.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefaultTruckService implements TruckService {
    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private BoxRepository boxRepository;

    @Override
    public Truck registerTruck(Truck truck) {
        return truckRepository.save(truck);
    }

    @Override
    public Truck addBoxesToTruck(String serialNumber, List<Box> newBoxes) throws TruckNotFoundException, TruckOverloadException {
        Optional<Truck> optionalTruck = truckRepository.findById(serialNumber);
        if (optionalTruck.isPresent()) {
            Truck truck = optionalTruck.get();
            Set<Box> initBoxes = truck.getBoxes();
            if (!isTruckOverload(initBoxes, newBoxes, truck.getWeightLimitInKg())) {
                newBoxes.forEach(box -> box.setTruck(truck));
                boxRepository.saveAll(newBoxes);
                initBoxes.addAll(newBoxes);
                return truckRepository.save(truck);
            } else {
                throw new TruckOverloadException(String.format("Can't add boxes to Truck (%s) because of overload", serialNumber));
            }
        } else {
            throw new TruckNotFoundException("Truck with serialNumber " + serialNumber + " is not found");
        }
    }

    @Override
    public List<Box> getBoxesByTruck(String serialNumber) throws TruckNotFoundException {
        Optional<Truck> optionalTruck = truckRepository.findById(serialNumber);
        if (optionalTruck.isPresent()) {
            return new ArrayList<>(optionalTruck.get().getBoxes());
        } else {
            throw new TruckNotFoundException("Truck with serialNumber " + serialNumber + " is not found");
        }
    }

    @Override
    public List<Truck> getAvailableTrucks() {
        return truckRepository.findByStateIn(List.of(TruckState.IDLE));
    }

    @Override
    public Short getBatteryLevelByTruck(String serialNumber) throws TruckNotFoundException {
        Optional<Truck> optionalTruck = truckRepository.findById(serialNumber);
        if (optionalTruck.isPresent()) {
            return optionalTruck.get().getBatteryCapacityInPercent();
        } else {
            throw new TruckNotFoundException("Truck with serialNumber " + serialNumber + " is not found");
        }
    }

    private boolean isTruckOverload(Collection<Box> initBoxes, Collection<Box> newBoxes, float weightLimit) {
        return getWeightOfBoxes(initBoxes) + getWeightOfBoxes(newBoxes) > weightLimit;
    }

    private float getWeightOfBoxes(Collection<Box> boxes) {
        return boxes.stream()
                .map(Box::getWeight)
                .reduce(0.0f, Float::sum);
    }
}
