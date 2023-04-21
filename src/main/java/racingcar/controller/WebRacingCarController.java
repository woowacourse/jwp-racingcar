package racingcar.controller;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.GameInitializeDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;
import racingcar.service.RacingCarService;

@RestController
public class WebRacingCarController {

    private final RacingCarService racingCarService;

    public WebRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping(path = "/plays")
    public RacingCarGameResultDto playGame(@RequestBody final GameInitializeDto gameInitializeDto) {
        return racingCarService.playRound(
                splitNames(gameInitializeDto.getNames()), gameInitializeDto.getCount());
    }

    @GetMapping("/plays")
    public List<RacingCarGameResultDto> findGameHistory() {
        return racingCarService.findGameHistory()
                .values().stream()
                .map(WebRacingCarController::toDto)
                .collect(toUnmodifiableList());
    }

    static List<String> splitNames(final String names) {
        return stream(names.split(",", -1))
                .collect(toUnmodifiableList());
    }

    private static RacingCarGameResultDto toDto(final List<Car> carList) {
        final Cars cars = new Cars(carList);
        return new RacingCarGameResultDto(cars.getWinners(), makeCarDtos(cars));
    }

    private static List<RacingCarDto> makeCarDtos(Cars cars) {
        return cars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName().getValue(), car.getDistance().getValue()))
                .collect(toUnmodifiableList());
    }
}
