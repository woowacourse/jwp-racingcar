package racingcar.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.HistoryResponseDto;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.repository.RacingGameRepository;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final NumberGenerator numberGenerator;
    private final RacingGameRepository racingGameRepository;

    public RacingCarService(NumberGenerator numberGenerator, @Qualifier("RacingGameRepositoryImpl") RacingGameRepository racingGameRepository) {
        this.numberGenerator = numberGenerator;
        this.racingGameRepository = racingGameRepository;
    }

    @Transactional
    public ResultResponseDto play(RacingGameRequestDto racingGameRequestDto) {
        RacingGame racingGame = initGame(racingGameRequestDto);
        racingGame.moveCars(numberGenerator);
        RacingGame save = racingGameRepository.save(racingGame);

        return new ResultResponseDto(save.getWinners(), mapToPlayerDtos(save.getCars()));
    }

    private RacingGame initGame(RacingGameRequestDto racingGameRequestDto) {
        Cars cars = Cars.of(racingGameRequestDto.getNames());
        return new RacingGame(cars, racingGameRequestDto.getCount());
    }

    private List<PlayerDto> mapToPlayerDtos(List<Car> cars) {
        return cars.stream()
                .map(car -> new PlayerDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    @Transactional
    public HistoryResponseDto getAllRacingGameHistory() {
        List<ResultResponseDto> resultResponseDtos = racingGameRepository.getAllRacingGames().stream()
                .map(racingGame ->
                        new ResultResponseDto(racingGame.getWinners(),
                                mapToPlayerDtos(racingGame.getCars())
                        )
                )
                .collect(Collectors.toList());
        return new HistoryResponseDto(resultResponseDtos);
    }
}
