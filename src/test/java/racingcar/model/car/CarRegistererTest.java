package racingcar.model.car;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarRegistererTest {

    @Test
    @DisplayName("자동차 이름을 등록하면 등록한대로 자동차를 준비하는지 검사한다.")
    void carPrepareTest() {
        CarRegisterer registerer = new CarRegisterer();
        registerer.registerCar("깃짱");
        registerer.registerCar("이리내");

        assertThat(registerer.prepareCars().getCurrentResult().get(0).getName()).isEqualTo("깃짱");
        assertThat(registerer.prepareCars().getCurrentResult().get(1).getName()).isEqualTo("이리내");
    }
}
