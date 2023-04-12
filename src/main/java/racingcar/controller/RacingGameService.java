package racingcar.controller;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    public GameResultDto playRacingGame(final RacingGameRequestDto racingGameRequestDto) {
        RacingGame racingGame = createRacingGame(racingGameRequestDto);
        play(racingGame);
        return new GameResultDto(
                mapWinnerNamesTextFrom(racingGame),
                mapCarDtosFrom(racingGame)
        );
    }

    private List<CarDto> mapCarDtosFrom(final RacingGame racingGame) {
        return racingGame.getCars().stream()
                .sorted(Comparator.comparingInt(Car::getPosition).reversed())
                .map(car -> new CarDto(car.getCarName(), String.valueOf(car.getPosition())))
                .collect(Collectors.toList());
    }

    private String mapWinnerNamesTextFrom(final RacingGame racingGame) {
        return racingGame.getWinners().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(","));
    }

    private void play(final RacingGame racingGame) {
        // TODO: 검증 로직 도메인 내로 이동 (미션 요구사항에 따라 2단계에서 수정 예정)
        while (racingGame.isGameOnGoing()) {
            racingGame.start();
        }
    }

    private RacingGame createRacingGame(final RacingGameRequestDto racingGameRequestDto) {
        return new RacingGame(
                List.of(racingGameRequestDto.getNames().split(",")),
                racingGameRequestDto.getCount(),
                new RandomNumberGenerator()
        );
    }
}
