package racing.dao;

import racing.controller.dto.request.CarRequest;

/**
 * @author 베베
 * @version 1.0.0
 * @since by 베베 on 2023. 04. 18.
 */
public interface GameDao {

    Long saveGame(int count);
    void saveCar(CarRequest request);

}
