package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.controller.dto.RacingInfoRequest;
import racingcar.controller.dto.RacingInfoResponse;
import racingcar.domain.CarGroup;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingResult;
import racingcar.domain.RandomNumberGenerator;

@RestController
public class RacingGameController {

    @PostMapping("/plays")
    public ResponseEntity<RacingInfoResponse> getPlayerNames(@RequestBody RacingInfoRequest request) {
        final String[] carNames = request.getNames().split(",");
        final CarGroup carGroup = new CarGroup(carNames);

        final RacingGame racingGame = new RacingGame(carGroup, new RandomNumberGenerator());
        for (int i = 0; i < request.getCount(); i++) {
            racingGame.race();
        }

        final RacingResult racingResult = racingGame.produceRacingResult();

        final RacingInfoResponse racingInfoResponse = new RacingInfoResponse(racingResult.pickWinner(), carGroup.getCars());
        return ResponseEntity.ok(racingInfoResponse);
    }
}
