package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.domain.Names;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;

@Controller
public class RacingGameController {

    @PostMapping("/plays")
    public ResponseEntity<RacingCarResponse> play(@RequestBody final RacingCarRequest racingCarRequest) {
        RacingCars racingCars = createRacingCar(racingCarRequest);
        TryCount tryCount = new TryCount(racingCarRequest.getTryCount());

        playGame(racingCars, tryCount);

        return ResponseEntity.ok().body(
                new RacingCarResponse(racingCars.getWinnerNames(), racingCars.getRacingCars()
                ));
    }

    private RacingCars createRacingCar(RacingCarRequest racingCarRequest) {
        Names names = new Names(racingCarRequest.getNames());
        return new RacingCars(createRacingCar(names));
    }

    private List<RacingCar> createRacingCar(Names names) {
        return names.getNames()
                .stream()
                .map(RacingCar::new)
                .collect(Collectors.toList());
    }

    private void playGame(RacingCars racingCars, TryCount tryCount) {
        while (canProceed(tryCount)) {
            racingCars.moveAll();
            tryCount = tryCount.deduct();
        }
    }

    private boolean canProceed(TryCount tryCount) {
        return tryCount.isOpportunity();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
