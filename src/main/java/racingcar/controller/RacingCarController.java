package racingcar.controller;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RandomNumberPicker;
import racingcar.domain.TryCount;
import racingcar.dto.GameInitializeDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping(path = "/plays")
    public RacingCarGameResultDto playGame(@RequestBody GameInitializeDto gameInitializeDto) {
        final Cars cars = Cars.from(splitNames(gameInitializeDto.getNames()));
        final TryCount tryCount = new TryCount(gameInitializeDto.getCount());
        racingCarService.playRound(cars, tryCount, RandomNumberPicker.getInstance());
        return new RacingCarGameResultDto(cars.getWinner(), makeCarDtos(cars));
    }

    @GetMapping("/plays")
    public List<RacingCarGameResultDto> findGameHistory() {
        return racingCarService.findGameHistory()
                .values().stream()
                .map(RacingCarController::toDto)
                .collect(toUnmodifiableList());
    }

    private static List<String> splitNames(final String names) {
        return stream(names.split(",", -1))
                .collect(toUnmodifiableList());
    }

    private static RacingCarGameResultDto toDto(final List<Car> carList) {
        final Cars cars = new Cars(carList);
        return new RacingCarGameResultDto(cars.getWinner(), makeCarDtos(cars));
    }

    private static List<RacingCarDto> makeCarDtos(Cars cars) {
        return cars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName().getValue(), car.getDistance().getValue()))
                .collect(toUnmodifiableList());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
