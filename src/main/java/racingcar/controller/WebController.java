package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto> playGame(final @RequestBody @Valid RequestDto requestDto) {
        List<String> carNames = NameFormatConverter.splitNameByDelimiter(requestDto.getNames());
        Cars cars = new Cars(carNames);

        gameService.executeRacingGame(cars, requestDto.getCount());
        return ResponseEntity.ok().body(new ResponseDto(cars.getWinners(), cars.getCars()));
    }
}
