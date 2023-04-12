package racingcar.controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingResultResponse;
import racingcar.repository.RacingCarRepository;
import racingcar.utils.RandomNumberGenerator;

@RestController
public class WebCarRacingController {

    private final RacingCarRepository racingCarRepository;

    public WebCarRacingController(RacingCarRepository racingCarRepository) {
        this.racingCarRepository = racingCarRepository;
    }

    @Transactional
    @PostMapping("/plays")
    public RacingResultResponse play(@RequestBody RacingCarRequest request) {
        RacingCars racingCars = getRacingCars(request.getNames());
        int count = request.getCount();

        RacingGame racingGame = new RacingGame(new RandomNumberGenerator(), racingCars);
        for (int i = 0; i < count; i++) {
            racingGame.moveCars();
        }
        List<String> winners = racingGame.getWinners();

        int gameId = racingCarRepository.saveGame(count);
        racingCarRepository.saveWinner(gameId, winners);
        racingCarRepository.saveCars(gameId, racingCars);

        List<RacingCarDto> racingCarDtos = racingCars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName(), car.getPosition()))
                .collect(toList());

        return new RacingResultResponse(winners, racingCarDtos);
    }

    private static RacingCars getRacingCars(String names) {
        return Arrays.stream(names.split(","))
                .map(Car::new)
                .collect(collectingAndThen(toList(), RacingCars::new));
    }
}
