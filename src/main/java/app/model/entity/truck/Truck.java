package app.model.entity.truck;

import app.model.entity.box.Box;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "truck")
public class Truck {
    @Id
    @Column(name="serial_number", length=100, nullable=false, unique=true)
    private String serialNumber;
    @Min(value = 0)
    @Max(value = 18_000, message = "Weight limit of truck is 18_000kg")
    @Column(name = "weight_limit_kg")
    private float weightLimitInKg;
    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "battery_capacity_prcnt")
    private Short batteryCapacityInPercent;
    @Enumerated(EnumType.STRING)
    private TruckState state;

    @OneToMany(mappedBy="truck", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Box> boxes = new HashSet<>();

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public float getWeightLimitInKg() {
        return weightLimitInKg;
    }

    public void setWeightLimitInKg(float weightLimitInKg) {
        this.weightLimitInKg = weightLimitInKg;
    }

    public Short getBatteryCapacityInPercent() {
        return batteryCapacityInPercent;
    }

    public void setBatteryCapacityInPercent(Short batteryCapacityInPercent) {
        this.batteryCapacityInPercent = batteryCapacityInPercent;
    }

    public TruckState getState() {
        return state;
    }

    public void setState(TruckState state) {
        this.state = state;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
    }
}
