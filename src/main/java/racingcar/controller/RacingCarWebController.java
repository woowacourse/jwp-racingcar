package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameInitializeDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;
import racingcar.service.RacingCarService;

@Controller
public class RacingCarWebController {
    private final RacingCarService racingCarService;

    public RacingCarWebController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public RacingCarGameResultDto run(@RequestBody GameInitializeDto gameInitializeDto) {
        Cars cars = new Cars(gameInitializeDto.getNames());
        TryCount tryCount = new TryCount(gameInitializeDto.getCount());
        racingCarService.playRound(cars, tryCount);
        racingCarService.saveGameResult(cars, tryCount);
        return new RacingCarGameResultDto(String.join(",", cars.getWinner()), makeCarDtos(cars));
    }

    @GetMapping("/plays")
    @ResponseBody
    public ResponseEntity<List<RacingCarGameResultDto>> showGameResult() {
        List<RacingCarGameResultDto> gameResult = racingCarService.getGameResult();
        return ResponseEntity.ok().body(gameResult);
    }

    private List<RacingCarDto> makeCarDtos(Cars cars) {
        return cars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName().getValue(), car.getPosition().getValue()))
                .collect(Collectors.toUnmodifiableList());
    }
}
