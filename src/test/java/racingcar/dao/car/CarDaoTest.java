package racingcar.dao.car;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.car.dto.CarRegisterRequest;
import racingcar.domain.Car;

@SpringBootTest
@Transactional
class CarDaoTest {

    @Autowired
    private CarDao carDao;

    @Test
    @DisplayName("자동차 등록 요청을 받아서 자동차를 DB에 저장한다.")
    void saveCar() {
        // given
        String name = "just";
        int position = 5;
        int playResultId = 1;
        CarRegisterRequest carRegisterRequest = new CarRegisterRequest(name, position, playResultId);

        // when
        carDao.save(carRegisterRequest);
        List<Car> findCars = carDao.findAllByPlayResultId(playResultId);
        Car findCar = findCars.get(0);

        // then
        assertThat(findCar.getName()).isEqualTo(name);
        assertThat(findCar.getPosition()).isEqualTo(position);
    }

    @Test
    @DisplayName("결과 ID를 받아서 모든 자동차를 조회한다.")
    void findAll() {
        // given, when
        carDao.save(new CarRegisterRequest("성하", 5, 1));
        carDao.save(new CarRegisterRequest("우르", 5, 1));
        carDao.save(new CarRegisterRequest("코코닥", 5, 1));
        carDao.save(new CarRegisterRequest("제이", 5, 1));
        List<Car> findCars = carDao.findAllByPlayResultId(1);

        Car car1 = findCars.get(0);
        Car car2 = findCars.get(1);
        Car car3 = findCars.get(2);
        Car car4 = findCars.get(3);

        // then
        assertThat(car1.getName()).isEqualTo("성하");
        assertThat(car2.getName()).isEqualTo("우르");
        assertThat(car3.getName()).isEqualTo("코코닥");
        assertThat(car4.getName()).isEqualTo("제이");
    }
}
