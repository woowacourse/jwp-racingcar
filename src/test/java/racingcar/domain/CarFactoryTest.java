package racingcar.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarFactoryTest {

    @DisplayName("입력 받은 자동차 이름들로 Car 객체를 생성하여 이를 관리하는 Cars 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"kim:true", "lee:true", "park:false"})
    void buildCarsTest(String name) {
        Cars cars = CarFactory.buildCars(List.of("kim", "lee"));
        List<String> carNames = cars.cars().stream()
                .map(car -> name)
                .collect(Collectors.toList());
        assertThat(carNames).contains(name);
    }
}
