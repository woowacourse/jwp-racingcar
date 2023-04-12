package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.PlaysRequest;
import racingcar.controller.dto.PlaysResponse;
import racingcar.domain.Race;
import racingcar.utils.NumberGenerator;

@RestController
public class RacingCarController {

    private final NumberGenerator numberGenerator;

    public RacingCarController(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlaysResponse> play(@RequestBody PlaysRequest playsRequest) {
        Race race = new Race(playsRequest.getCount(), playsRequest.getNames(), numberGenerator);
        while (!race.isFinished()) {
            race.playRound();
        }
        PlaysResponse response = PlaysResponse.from(race.findWinners(), race.getParticipants());
        return ResponseEntity.ok().body(response);
    }
}
