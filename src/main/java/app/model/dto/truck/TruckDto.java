package app.model.dto.truck;

import app.model.entity.truck.TruckState;
import app.model.dto.box.BoxDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TruckDto {
    private String serialNumber;
    private float weightLimitInKg;
    private Short batteryCapacityInPercent;
    private TruckState state;
    private Set<BoxDto> boxes = new HashSet<>();
}
