package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameInitializeDto;
import racingcar.dto.RacingCarGameResultDto;
import racingcar.service.RacingCarService;

@Controller
public class RacingCarWebController {
    private final RacingCarService racingCarService;

    public RacingCarWebController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public RacingCarGameResultDto run(@RequestBody GameInitializeDto gameInitializeDto) {
        Cars cars = Cars.from(gameInitializeDto.getNames());
        TryCount tryCount = new TryCount(gameInitializeDto.getCount());
        return racingCarService.play(cars, tryCount);
    }

    @GetMapping("/plays")
    @ResponseBody
    public ResponseEntity<List<RacingCarGameResultDto>> showGameResult() {
        List<RacingCarGameResultDto> gameResult = racingCarService.getAllGameResult();
        return ResponseEntity.ok().body(gameResult);
    }
}
