package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.CarDTO;
import racingcar.dto.ResultDTO;
import racingcar.dto.RacingGameRequestDTO;
import racingcar.dto.RacingGameResponseDTO;
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
    public RacingGameResponseDTO createGame(@RequestBody RacingGameRequestDTO racingGameRequestDTO) {
        final List<String> carNames = Arrays.stream(racingGameRequestDTO.getNames().split(DELIMITER))
                .collect(Collectors.toList());
        final ResultDTO resultDTO = racingCarService.play(carNames, racingGameRequestDTO.getCount());
        final String winnerNames = resultDTO.getWinners().stream()
                .collect(Collectors.joining(DELIMITER));
        return new RacingGameResponseDTO(winnerNames, resultDTO.getCarDTOs());
    }

    @GetMapping("/plays")
    public List<RacingGameResponseDTO> getGameResults() {
        final List<RacingGameResponseDTO> racingGameResponseDTOs = new ArrayList<>();

        final List<ResultDTO> resultDTOs = racingCarService.getSavedGames();

        for (ResultDTO resultDTO : resultDTOs) {
            String winnerNames = resultDTO.getWinners()
                    .stream()
                    .collect(Collectors.joining(DELIMITER));
            racingGameResponseDTOs.add(new RacingGameResponseDTO(winnerNames, resultDTO.getCarDTOs()));
        }
        return new ArrayList<>(racingGameResponseDTOs);
    }
}
