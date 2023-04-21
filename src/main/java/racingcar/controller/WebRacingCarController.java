package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.ResultDTO;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.exception.CarException;
import racingcar.exception.CarsException;
import racingcar.service.RacingCarService;

@RestController
public class WebRacingCarController {
    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;

    public WebRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public RacingGameResponse createGame(@RequestBody RacingGameRequest racingGameRequest) {
        final List<String> carNames = Arrays.stream(racingGameRequest.getNames().split(DELIMITER))
                .collect(Collectors.toList());
        final ResultDTO resultDTO = racingCarService.play(carNames, racingGameRequest.getCount());
        final String winnerNames = String.join(DELIMITER, resultDTO.getWinners());
        return new RacingGameResponse(winnerNames, resultDTO.getCarDTOs());
    }

    @GetMapping("/plays")
    public List<RacingGameResponse> getGameResults() {
        final List<RacingGameResponse> racingGameResponses = new ArrayList<>();
        final List<ResultDTO> resultDTOs = racingCarService.getSavedGames();

        for (ResultDTO resultDTO : resultDTOs) {
            final String winnerNames = String.join(DELIMITER, resultDTO.getWinners());
            racingGameResponses.add(new RacingGameResponse(winnerNames, resultDTO.getCarDTOs()));
        }
        return new ArrayList<>(racingGameResponses);
    }
}
