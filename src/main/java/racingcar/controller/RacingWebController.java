package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.GameResult;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;
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

        List<RacingCarResultDto> results = racingGameService.run(carNames, count);

        List<String> winners = results.stream()
                .filter(result -> result.isWin() == GameResult.WIN.getValue())
                .map(RacingCarResultDto::getName)
                .collect(Collectors.toList());

        List<RacingCarDto> racingCarDtos = results.stream()
                .map(it -> RacingCarDto.of(it.getName(), it.getPosition()))
                .collect(Collectors.toList());

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
