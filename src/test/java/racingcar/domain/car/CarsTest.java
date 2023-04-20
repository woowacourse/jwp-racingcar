package racingcar.domain.car;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarsTest {
    @Test
    void 우승자_목록을_반환한다() {
        // given
        final Cars cars = new Cars("아벨,스플릿,포비");
        final List<Car> carList = cars.getCars();
        
        // when
        final Car abel = carList.get(0);
        final Car movedAbel = abel.move(() -> true);
        carList.set(carList.indexOf(abel), movedAbel);
        
        final Car split = carList.get(1);
        final Car movedSplit = split.move(() -> true);
        carList.set(carList.indexOf(split), movedSplit);
        List<Car> winners = cars.getWinners();
        
        // then
        final Car expectedAbel = new Car(new Name("아벨"));
        final Car expectedSplit = new Car(new Name("스플릿"));
        assertThat(winners).containsExactly(expectedAbel.move(() -> true), expectedSplit.move(() -> true));
    }
    
    @ParameterizedTest(name = "names : {0}")
    @ValueSource(strings = {"아벨,아벨,스플릿,포비", "aa,aa,bb,cc"})
    void 같은_이름이_존재할_시_예외_처리(String names) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Cars(names))
                .withMessageStartingWith("[ERROR] 동명이인은 불가능합니다. 현재 차 목록 : \n");
    }
}
