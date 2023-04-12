package racingcar.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.dto.RacingCarDto;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;
import racingcar.validator.Validator;

@RestController
public class RacingWebController {
    private final RacingGameService racingGameService;

    public RacingWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays")
    public GameResponseDto play(@RequestBody GameRequestDto gameRequestDto) {
        List<String> carNames = getValidCarNames(gameRequestDto.getNames());
        int count = getValidTryCount(gameRequestDto.getCount());

        long id = racingGameService.run(carNames, count);

        List<String> winners = racingGameService.findWinnersById(id);
        List<RacingCarDto> racingCarDtos = racingGameService.findCarsById(id);
        return new GameResponseDto(winners, racingCarDtos);
    }

    private List<String> getValidCarNames(String carNames) {
        List<String> parsedCarNames = Parser.parsing(carNames, ",");
        Validator.validateNames(parsedCarNames);
        return parsedCarNames;
    }

    private int getValidTryCount(int tryCount) {
        Validator.validateTryCount(tryCount);
        return tryCount;
    }
}
