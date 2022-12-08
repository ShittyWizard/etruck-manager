package app.model.entity.box;

import app.model.entity.truck.Truck;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@Table(name = "box")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Min(value = 0)
    private float weight;
    @Pattern(regexp = "[A-Z_\\d*]*")
    private String code;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="truck_id")
    private Truck truck;

    public Box(float weight, String code, String imageUrl, Truck truck) {
        this.weight = weight;
        this.code = code;
        this.imageUrl = imageUrl;
        this.truck = truck;
    }

    public Integer getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
