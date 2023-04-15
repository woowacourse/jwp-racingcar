package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingCarsTest {


    @Test
    @DisplayName("우승자 선정 기능 - 우승자가 1명일 경우 테스트")
    void getOneWinnersTest() {
        final RacingCars racingCars = RacingCars.makeCars("test1,test2,test3,test4,test5");

        final Car test1 = racingCars.getCars().get(0);
        test1.move(4);

        Assertions.assertThat(racingCars.getWinners().size()).isEqualTo(1);
        Assertions.assertThat(racingCars.getWinners()).containsExactly(test1);
    }

    @Test
    @DisplayName("우승자 선정 기능 - 우승자가 복수일 경우 테스트")
    void getMultipleWinnersTest() {
        final RacingCars racingCars = RacingCars.makeCars("test1,test2,test3,test4,test5");

        final Car test1 = racingCars.getCars().get(0);
        final Car test2 = racingCars.getCars().get(1);
        final Car test3 = racingCars.getCars().get(2);

        test1.move(4);
        test2.move(4);
        test3.move(4);

        Assertions.assertThat(racingCars.getWinners().size()).isEqualTo(3);
        Assertions.assertThat(racingCars.getWinners()).containsOnly(test1, test2, test3);
    }


    @Test
    @DisplayName("우승자 선정 기능 - 모두 동점일 경우 테스트")
    void getAllWinnersTest() {
        final RacingCars racingCars = RacingCars.makeCars("test1,test2,test3,test4,test5");

        final Car test1 = racingCars.getCars().get(0);
        final Car test2 = racingCars.getCars().get(1);
        final Car test3 = racingCars.getCars().get(2);
        final Car test4 = racingCars.getCars().get(3);
        final Car test5 = racingCars.getCars().get(4);

        test1.move(4);
        test2.move(4);
        test3.move(4);
        test4.move(4);
        test5.move(4);

        Assertions.assertThat(racingCars.getWinners().size()).isEqualTo(5);
        Assertions.assertThat(racingCars.getWinners()).containsOnly(test1, test2, test3, test4, test5);
    }

    @Test
    @DisplayName("분리한 자동차 이름이 중복된 경우")
    void splitCarNamesDuplicateTest() {
        String carNames = "성하,성하,이오";

        Assertions.assertThatThrownBy(() -> RacingCars.makeCars(carNames))
                  .isInstanceOf(IllegalArgumentException.class);
    }
}
