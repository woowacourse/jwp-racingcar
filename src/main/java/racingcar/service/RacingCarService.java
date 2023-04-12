package racingcar.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.request.RacingCarRequestDto;
import racingcar.dto.response.RacingGameResponseDto;

@Service
public class RacingCarService {

    public RacingGameResponseDto play(final RacingCarRequestDto racingCarRequestDto) {
        final RacingGame racingGame = generateRacingGame(racingCarRequestDto);
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
        return RacingGameResponseDto.of(racingGame.findWinners(), racingGame.getCurrentResult());
    }

    private static RacingGame generateRacingGame(final RacingCarRequestDto racingCarRequestDto) {
        final List<String> names = Arrays.stream(racingCarRequestDto.getNames().split(","))
                .collect(Collectors.toList());
        return new RacingGame(new RandomNumberGenerator(), new Cars(names), new Count(racingCarRequestDto.getCount()));
    }
}
