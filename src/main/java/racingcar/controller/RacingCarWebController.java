package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.model.RacingCarRequest;
import racingcar.model.RacingCarResponse;
import racingcar.model.RacingCarResult;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarWebController {
    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;

    @Autowired
    public RacingCarWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<RacingCarResult> play(@RequestBody final RacingCarRequest request) {
        final List<String> names = Arrays.stream(request.getNames().split(DELIMITER))
                .collect(Collectors.toList());
        final int count = request.getCount();

        final RacingCarResult racingCarResult = racingCarService.playRacingCar(names, count);

        return ResponseEntity.ok().body(racingCarResult);
    }

    @GetMapping(path = "/plays")
    public List<RacingCarResponse> getLog() {
        return racingCarService.getRacingCarLog();
    }
}
