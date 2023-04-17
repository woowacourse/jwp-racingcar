package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.ResultDTO;
import racingcar.request.RacingGameRequest;
import racingcar.response.RacingGameResponse;
import racingcar.exception.CarException;
import racingcar.exception.CarsException;
import racingcar.service.RacingCarService;

@RestController
public class WebController {
    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;

    public WebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    @ExceptionHandler(value = {CarException.class, CarsException.class})
    public RacingGameResponse createGame(@RequestBody RacingGameRequest racingGameRequest) {
        final List<String> carNames = Arrays.stream(racingGameRequest.getNames().split(DELIMITER))
                .collect(Collectors.toList());
        final ResultDTO resultDTO = racingCarService.play(carNames, racingGameRequest.getCount());
        final String winnerNames = resultDTO.getWinners().stream()
                .collect(Collectors.joining(DELIMITER));
        return new RacingGameResponse(winnerNames, resultDTO.getCarDTOs());
    }

    @GetMapping("/plays")
    public List<RacingGameResponse> getGameResults() {
        final List<RacingGameResponse> racingGameResponses = new ArrayList<>();
        final List<ResultDTO> resultDTOs = racingCarService.getSavedGames();

        for (ResultDTO resultDTO : resultDTOs) {
            String winnerNames = resultDTO.getWinners()
                    .stream()
                    .collect(Collectors.joining(DELIMITER));
            racingGameResponses.add(new RacingGameResponse(winnerNames, resultDTO.getCarDTOs()));
        }
        return new ArrayList<>(racingGameResponses);
    }
}
