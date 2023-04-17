package racingcar.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class RacingResultTest {
    @DisplayName("레이싱 게임 결과를 생성한다.")
    @Test
    void create() {
        // given
        final List<Car> cars = List.of(new Car("헤나"), new Car("찰리"));

        // expect
        Assertions.assertDoesNotThrow(() -> new RacingResult(cars));
    }

    @DisplayName("한 명의 레이싱 게임 승리자를 조회한다.")
    @Test
    void pickWinner() {
        // given
        final List<Car> cars = List.of(new Car("헤나"));

        // when
        final RacingResult racingResult = new RacingResult(cars);
        final List<String> nameValues = racingResult.pickWinner()
                .stream()
                .map(Name::getValue)
                .collect(Collectors.toList());

        // then
        assertThat(nameValues).containsExactly("헤나");
    }

    @DisplayName("다수의 레이싱 게임 승리자를 조회한다.")
    @Test
    void pickMultiWinners() {
        // given
        final List<Car> cars = List.of(new Car("헤나"), new Car("찰리"), new Car("저문"));

        // when
        final RacingResult racingResult = new RacingResult(cars);
        final List<String> nameValues = racingResult.pickWinner()
                .stream()
                .map(Name::getValue)
                .collect(Collectors.toList());

        // then
        assertThat(nameValues).containsExactly("헤나", "찰리", "저문");
    }

    @DisplayName("레이싱 게임 결과 히스토리를 조회한다.")
    @Test
    void getHistory() {
        // given
        final List<Car> cars = List.of(new Car("헤나"), new Car("찰리"), new Car("저문"));

        // when
        final RacingResult racingResult = new RacingResult(cars);
        final LinkedHashMap<Name, Position> history = racingResult.getHistory();

        // then
        assertThat(history).containsExactly(
                entry(new Name("헤나"), Position.ZERO),
                entry(new Name("찰리"), Position.ZERO),
                entry(new Name("저문"), Position.ZERO)
        );
    }
}
