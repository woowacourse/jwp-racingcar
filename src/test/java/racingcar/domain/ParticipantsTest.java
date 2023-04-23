package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.utils.DrivableNumberGenerator;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {
    @Test
    void 참가자_등록_테스트() {
        //given
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        final Car car1 = new Car("test1", numberGenerator);
        final Car car2 = new Car("test2", numberGenerator);
        //when
        //then
        Participants participants = new Participants(List.of(car1, car2));
        List<Car> cars = participants.getCars();

        Assertions.assertThat(cars.size()).isEqualTo(2);
        Assertions.assertThat(cars.get(0).getName()).isEqualTo("test1");
        Assertions.assertThat(cars.get(1).getName()).isEqualTo("test2");
    }

    @Test
    void 참가자_이름은_중복될_수_없다() {
        //given
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        final Car car1 = new Car("test1", numberGenerator);
        final Car car2 = new Car("test1", numberGenerator);
        //when then
        assertThatThrownBy(() -> {
            new Participants(List.of(car1, car2));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] ")
                .hasMessageContaining("자동차 이름은 중복될 수 없습니다.");
    }

    @Test
    void 가장_멀리간_차량이_승자이다() {
        //given
        NumberGenerator numberGenerator = new DrivableNumberGenerator();
        final Car car1 = new Car("test1", numberGenerator);
        final Car car2 = new Car("test2", numberGenerator);
        final Car car3 = new Car("test3", numberGenerator);
        Participants participants = new Participants(List.of(car1, car2, car3));

        // when
        car1.drive();
        car1.drive();
        car2.drive();
        List<Car> winners = participants.findWinners();

        // then
        assertThat(winners.size()).isEqualTo(1);
        assertThat(winners.get(0).getName()).isEqualTo("test1");
    }

    @Test
    void 가장_멀리간_차량이_여러대이면_승자는_여러명이다() {
        //given
        NumberGenerator numberGenerator = new DrivableNumberGenerator();
        final Car car1 = new Car("test1", numberGenerator);
        final Car car2 = new Car("test2", numberGenerator);
        final Car car3 = new Car("test3", numberGenerator);
        Participants participants = new Participants(List.of(car1, car2, car3));

        // when
        car1.drive();
        car1.drive();
        car2.drive();
        car2.drive();
        car3.drive();
        List<Car> winners = participants.findWinners();

        // then
        assertThat(winners.size()).isEqualTo(2);
        assertThat(winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList())).containsExactly("test1", "test2");
    }

}
