package racingcar.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.CarRandomNumberGenerator;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.service.RacingGameService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebRacingCarController {

    private final RacingGameService racingGameService;

    public WebRacingCarController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<CarGameResponse> plays(@RequestBody CarGameRequest request) {
        List<String> names = Arrays.stream(request.getNames().split(",")).collect(Collectors.toList());
        RacingGame game = new RacingGame(new CarRandomNumberGenerator(), new Cars(names), request.getCount());
        CarGameResponse result = racingGameService.play(game);
        return ResponseEntity.ok(result);
    }
}
