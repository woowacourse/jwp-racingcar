package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Cars;
import racingcar.domain.vo.Trial;
import racingcar.dto.*;
import racingcar.service.WebService;

import java.util.List;
import racingcar.utils.Converter;
import racingcar.utils.RandomNumberGenerator;

@RestController
public class WebController {

    final private WebService racingService;

    public WebController(WebService webService) {
        this.racingService = webService;
    }

    @PostMapping("/plays")
    public RacingResultResponse run(@RequestBody RacingRequest dto) {
        Cars cars = new Cars(dto.getNames(), RandomNumberGenerator.makeInstance());
        Trial trial = Trial.of(Converter.convertStringToInt(dto.getCount()));
        Cars movedCars = racingService.run(cars, trial);
        return new RacingResultResponse(movedCars.getWinnerNames(), movedCars.getCarDtos());
    }

    @GetMapping("/plays")
    public List<RacingResultResponse> run() {
        return racingService.loadHistory();
    }
}
