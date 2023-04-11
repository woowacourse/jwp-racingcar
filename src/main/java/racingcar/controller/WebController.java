package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.model.Cars;
import racingcar.service.GameService;

import java.util.Arrays;
import java.util.List;

@Controller
public class WebController {

    private final GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public ResponseDto playGame(@RequestBody RequestDto requestDto) {
        // TODO: 2023/04/11 이름 중복 검사
        List<String> carNames = Arrays.asList(requestDto.getNames().split(","));
        Cars cars = new Cars(carNames);

        gameService.moveCars(cars, requestDto.getCount());

        return new ResponseDto(cars.getWinners(), cars.getCars());
    }
}
