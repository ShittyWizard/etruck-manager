package app.repository;

import app.model.entity.truck.Truck;
import app.model.entity.truck.TruckState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends JpaRepository<Truck, String> {
    List<Truck> findByStateIn(List<TruckState> states);
}
