package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameInitializeDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;

@Controller
public class RacingCarController {
    @PostMapping(path = "/plays")
    @ResponseBody
    public RacingCarGameResultDto run(@RequestBody GameInitializeDto gameInitializeDto) {
        Cars cars = new Cars(gameInitializeDto.getNames());
        TryCount tryCount = new TryCount(gameInitializeDto.getCount());
        playRound(cars, tryCount);
        return new RacingCarGameResultDto(cars.getWinner(), makeCarDtos(cars));
    }

    private void playRound(Cars cars, TryCount tryCount) {
        for (int i = 0; i < tryCount.getValue(); i++) {
            cars.runRound();
        }
    }

    private List<RacingCarDto> makeCarDtos(Cars cars) {
        return cars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName().getValue(), car.getDistance().getValue()))
                .collect(Collectors.toUnmodifiableList());
    }
}
