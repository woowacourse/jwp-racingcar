package racing.dao;

import racing.controller.dto.request.CarRequest;

import java.util.List;

/**
 * @author 베베
 * @version 1.0.0
 * @since by 베베 on 2023. 04. 18.
 */
public interface CarDao {

    void saveCar(CarRequest request);

    List<CarEntity> findAll();

}
