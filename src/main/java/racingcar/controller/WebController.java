package racingcar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.model.Cars;
import racingcar.service.GameService;
import racingcar.util.NameFormatConverter;

@RestController
public class WebController {

    private final GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResponseDto> playGame(@RequestBody RequestDto requestDto) {
        List<String> carNames = NameFormatConverter.splitNameByDelimiter(requestDto.getNames());
        Cars cars = new Cars(carNames);

        gameService.executeRacingGame(cars, requestDto.getCount());

        final ResponseDto responseDto = new ResponseDto(cars.getWinners(), cars.getCars());

        return ResponseEntity.ok()
                .body(responseDto);
    }
}
