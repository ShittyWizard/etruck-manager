package app.model.mapper.truck;

import app.model.entity.truck.Truck;
import app.model.mapper.box.BoxMapper;
import app.model.dto.truck.TruckDto;

import java.util.stream.Collectors;

public class TruckMapper {
    public static Truck toEntity(TruckDto truck) {
        if (truck == null) {
            return null;
        }
        return new Truck(
                truck.getSerialNumber(),
                truck.getWeightLimitInKg(),
                truck.getBatteryCapacityInPercent(),
                truck.getState(),
                truck.getBoxes().stream()
                        .map(BoxMapper::toEntity)
                        .collect(Collectors.toSet())
        );
    }

    public static TruckDto toDto(Truck truckEntity) {
        if (truckEntity == null) {
            return null;
        }
        return new TruckDto(
                truckEntity.getSerialNumber(),
                truckEntity.getWeightLimitInKg(),
                truckEntity.getBatteryCapacityInPercent(),
                truckEntity.getState(),
                truckEntity.getBoxes().stream()
                        .map(BoxMapper::toDto)
                        .collect(Collectors.toSet())
        );
    }
}
