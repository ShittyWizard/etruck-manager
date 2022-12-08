package app.controller;

import app.exception.TruckNotFoundException;
import app.exception.TruckOverloadException;
import app.model.dto.box.BoxDto;
import app.model.entity.box.Box;
import app.model.entity.truck.Truck;
import app.model.mapper.box.BoxMapper;
import app.model.mapper.truck.TruckMapper;
import app.model.dto.truck.TruckDto;
import app.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/truck")
public class TruckController {
    @Autowired
    private TruckService truckService;

    @GetMapping
    private ResponseEntity<List<TruckDto>> getTrucks() {
        List<TruckDto> availableTrucks = truckService.getAvailableTrucks().stream()
                .map(TruckMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(200)
                .body(availableTrucks);
    }

    @PostMapping
    private ResponseEntity<TruckDto> registerTruck(@RequestBody TruckDto truck) {
        Truck registeredTruck = truckService.registerTruck(TruckMapper.toEntity(truck));
        return ResponseEntity.status(200)
                .body(TruckMapper.toDto(registeredTruck));
    }

    @PostMapping("/{truckSerialNumber}")
    private ResponseEntity<TruckDto> addBoxesToTruck(@PathVariable String truckSerialNumber, @RequestBody List<BoxDto> boxes) throws TruckOverloadException, TruckNotFoundException {
        List<Box> newBoxes = boxes.stream()
                .map(BoxMapper::toEntity)
                .collect(Collectors.toList());
        Truck truckEntity = truckService.addBoxesToTruck(truckSerialNumber, newBoxes);
        TruckDto updatedTruck = TruckMapper.toDto(truckEntity);
        return ResponseEntity.status(200)
                .body(updatedTruck);
    }

    @GetMapping("/{truckSerialNumber}/battery")
    private ResponseEntity<Short> checkBatteryLevel(@PathVariable String truckSerialNumber) throws TruckNotFoundException {
        return ResponseEntity.status(200)
                .body(truckService.getBatteryLevelByTruck(truckSerialNumber));
    }

    @GetMapping("/{truckSerialNumber}/boxes")
    private ResponseEntity<List<BoxDto>> getBoxesByTruck(@PathVariable String truckSerialNumber) throws TruckNotFoundException {
        List<BoxDto> boxesByTruck = truckService.getBoxesByTruck(truckSerialNumber).stream()
                .map(BoxMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(200)
                .body(boxesByTruck);
    }
}
