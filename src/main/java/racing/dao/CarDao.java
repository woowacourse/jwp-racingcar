package racing.dao;

import org.springframework.stereotype.Repository;
import racing.controller.dto.request.CarRequest;

import java.util.List;

public interface CarDao {

    void saveCar(CarRequest request);

    List<CarEntity> findAll();

}
