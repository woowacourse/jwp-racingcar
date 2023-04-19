package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RacingCars;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.dto.RacingCarDto;
import racingcar.exception.CommaNotFoundException;
import racingcar.service.RacingGameService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;
import static java.util.stream.Collectors.toList;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public GameResponse doGame(@RequestBody final GameRequest gameRequest) {
        final RacingCars racingCars = racingGameService.run(sliceNameByComma(gameRequest.getNames()), gameRequest.getCount());

        final List<String> winnerNames = racingCars.getWinnerNames();
        final String winnerName = String.join(", ", winnerNames);

        final List<RacingCarDto> racingCarsDto = racingCars.getRacingCars().stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(toList());

        return new GameResponse(winnerName, racingCarsDto);
    }

    private List<String> sliceNameByComma(final String names) {
        validateComma(names);

        return getSplitName(names);
    }

    private void validateComma(final String names) {
        if (!names.contains(",")) {
            throw new CommaNotFoundException();
        }
    }

    private List<String> getSplitName(final String names) {
        List<String> splitNames = new ArrayList<>();
        addAll(splitNames, names.split(","));

        return splitNames;
    }
}
