package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.GameResult;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;
import racingcar.validator.Validator;

@RestController
public class RacingWebController {
    private final RacingGameService racingGameService;
    private final Logger logger;

    public RacingWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @PostMapping(path = "/plays")
    public PlayResponseDto play(@RequestBody PlayRequestDto playRequestDto) {
        List<String> carNames = getValidCarNames(playRequestDto.getNames());
        int count = getValidTryCount(playRequestDto.getCount());

        List<RacingCarResultDto> results = racingGameService.run(carNames, count);

        return makePlayResponseDto(results);
    }

    @GetMapping(path = "/plays")
    public List<PlayResponseDto> getHistory() {
        List<List<RacingCarResultDto>> resultsLog = racingGameService.history();

        return resultsLog.stream()
                .map(RacingWebController::makePlayResponseDto)
                .collect(Collectors.toList());
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

    private static PlayResponseDto makePlayResponseDto(List<RacingCarResultDto> results) {
        List<String> winners = getWinners(results);
        List<RacingCarDto> racingCarDtos = getCarInfos(results);
        return new PlayResponseDto(winners, racingCarDtos);
    }

    private static List<RacingCarDto> getCarInfos(List<RacingCarResultDto> results) {
        return results.stream()
                .map(it -> RacingCarDto.of(it.getName(), it.getPosition()))
                .collect(Collectors.toList());
    }

    private static List<String> getWinners(List<RacingCarResultDto> results) {
        return results.stream()
                .filter(result -> result.isWin() == GameResult.WIN.getValue())
                .map(RacingCarResultDto::getName)
                .collect(Collectors.toList());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(IllegalArgumentException e) {
        logger.error(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e) {
        logger.error(e.getMessage());
        return e.getMessage();
    }
}
