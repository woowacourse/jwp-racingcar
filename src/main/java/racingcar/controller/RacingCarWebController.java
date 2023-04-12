package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.service.RaceResultService;

@RestController
public class RacingCarWebController {

    private static final String CAR_NAMES_BLANK_ERROR = "[ERROR] 경주할 자동차 이름이 입력되지 않았습니다.";
    private static final String TRY_NUM_NOT_POSITIVE_ERROR = "[ERROR] 시도할 횟수는 1 이상이어야 합니다.";

    private final RaceResultService raceResultService;

    public RacingCarWebController(final RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }

    @PostMapping("/plays")
    public RaceResultResponse registerRaceResult(@RequestBody GameInfoRequest gameInfoRequest) {
        final String names = gameInfoRequest.getNames();
        if (names.length() < 1) {
            throw new IllegalArgumentException(CAR_NAMES_BLANK_ERROR);
        }

        final int trialCount = gameInfoRequest.getCount();
        if (trialCount <= 0) {
            throw new IllegalArgumentException(TRY_NUM_NOT_POSITIVE_ERROR);
        }

        return raceResultService.createRaceResult(gameInfoRequest);
    }
}
