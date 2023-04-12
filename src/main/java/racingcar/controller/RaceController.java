package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;

@RestController
public class RaceController {

    private final RaceService raceService;

    public RaceController(final RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping("/plays")
    public RaceResponse play(@RequestBody final RaceRequest raceRequest) {
        return raceService.getRaceResults(raceRequest);
    }
}
