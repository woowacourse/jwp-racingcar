package racingcar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.MoveRequestDto;
import racingcar.dto.MoveResponseDto;
import racingcar.dto.PlayResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class RacingCarServiceTest {

    @Autowired
    RacingCarService racingCarService;

    @Test
    void moveCar_메서드는_요청정보를_바탕으로_자동차를_움직이고_결과를_반환해준다() {
        //given
        final MoveRequestDto moveRequestDto = new MoveRequestDto(List.of("a", "b"), 5);

        //when
        final MoveResponseDto moveResponseDto = racingCarService.moveCar(moveRequestDto);

        //then
        assertThat(moveResponseDto.getRacingCars()).hasSize(2);
    }

    @Test
    void moveCar_에서_5자가_넘는_자동차_이름이_들어가면_예외가_발생한다() {
        //given
        final MoveRequestDto moveRequestDto = new MoveRequestDto(List.of("123456"), 5);

        //expect
        assertThatThrownBy(() -> racingCarService.moveCar(moveRequestDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void moveCar_에서_시도_횟수가_0_이하이면_예외가_발생한다(int count) {
        //given
        final MoveRequestDto moveRequestDto = new MoveRequestDto(List.of("A", "B"), count);

        //expect
        assertThatThrownBy(() -> racingCarService.moveCar(moveRequestDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findAllGameHistory_는_이전_게임_히스토리를_모두_가져온다() {
        //given
        final MoveRequestDto moveRequestDto = new MoveRequestDto(List.of("A", "B"), 5);
        for (int i = 0; i < 5; i++) {
            racingCarService.moveCar(moveRequestDto);
        }

        //when
        final List<PlayResponseDto> allGameHistory = racingCarService.findAllGameHistory();

        //then
        assertThat(allGameHistory).hasSize(5);
    }
}
