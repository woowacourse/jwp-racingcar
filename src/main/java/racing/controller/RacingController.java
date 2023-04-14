package racing.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.request.validator.RacingGameInfoRequestValidator;
import racing.controller.dto.request.validator.RequestValidator;
import racing.controller.dto.response.RacingCarStateResponse;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.domain.CarFactory;
import racing.domain.Cars;
import racing.service.RacingGameService;

@RestController
public class RacingController {

    private final RacingGameService racingGameService;

    public RacingController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResultResponse> playGame(@RequestBody RacingGameInfoRequest request) {
        RequestValidator<RacingGameInfoRequest> requestValidator = new RacingGameInfoRequestValidator();
        requestValidator.validate(request);

        Cars cars = CarFactory.carFactory(request.getNames());
        Long gameId = racingGameService.saveGameByCount(request.getCount());
        racingGameService.playGame(gameId, cars);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getRacingGameResultResponse(cars));
    }

    private RacingGameResultResponse getRacingGameResultResponse(Cars cars) {
        List<RacingCarStateResponse> racingCarsState = cars.getCars().stream()
                .map(car -> new RacingCarStateResponse(car.getName(), car.getStep()))
                .collect(Collectors.toList());

        return new RacingGameResultResponse(racingGameService.getWinners(cars), racingCarsState);
    }

}
