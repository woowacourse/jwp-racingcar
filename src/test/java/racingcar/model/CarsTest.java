package racingcar.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.Strategy.MovableNumberGenerator;

class CarsTest {

    @Test
    @DisplayName("참여자명 리스트로 cars를 생성하는지 확인")
    void from() {
        List<String> names = List.of("name1", "name2");
        assertDoesNotThrow(() ->Cars.from(names));
    }

    @Test
    @DisplayName("경주 참여 인원 두명 미만인 경우 예외")
    void validateOverMinimumParticipant() {
        assertThatThrownBy(() -> Cars.from(List.of("name1")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경주는 최소 2명이 필요해요.");
    }

    @Test
    @DisplayName("경주 참여자들이 모두 같은 거리를 이동했을때 전부 승자가 되는지 확인")
    void findWinners() {
        Cars cars = Cars.from(List.of("name1", "name2", "name3"));
        cars.move(3, new MovableNumberGenerator());

        assertThat(cars.findWinners().size()).isEqualTo(3);
    }
}
