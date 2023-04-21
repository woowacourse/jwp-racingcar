package racingcar.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;

class RacingCarServiceTest {

    private final RacingCarService racingCarService = new RacingCarService(new RacingCarDummyDao());

    @Test
    void addRaceTest() {
        RacingCarRequestDto racingCarRequestDto = new RacingCarRequestDto("a,b,c", 3);
        RacingCarResponseDto racingCarResponseDto = racingCarService.addRace(racingCarRequestDto);
        assertThat(racingCarResponseDto.getRacingCars()).hasSize(3);
    }

    @Test
    void findRaceTest() {
        RacingCarRequestDto racingCarRequestDto = new RacingCarRequestDto("a,b,c", 3);
        racingCarService.addRace(racingCarRequestDto);
        assertThat(racingCarService.findRace().get(0).getRacingCars()).hasSize(3);
    }
}
