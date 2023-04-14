package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.model.Cars;
import racingcar.service.GameService;
import racingcar.util.NameFormatConverter;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController {

    private final GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponseDto> playGame(final @RequestBody @Valid RacingGameRequestDto racingGameRequestDto) {
        List<String> carNames = NameFormatConverter.splitNameByDelimiter(racingGameRequestDto.getNames());
        Cars cars = new Cars(carNames);

        gameService.executeRacingGame(cars, racingGameRequestDto.getCount());
        return ResponseEntity.ok().body(new RacingGameResponseDto(cars.getWinners(), cars.getCars()));
    }
}
