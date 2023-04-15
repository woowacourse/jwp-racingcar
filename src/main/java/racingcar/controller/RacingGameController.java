package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.service.RacingGameService;
import racingcar.utils.RandomNumberGenerator;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    @Autowired
    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(value = "/plays", consumes = "application/json")
    @ResponseBody
    public ResponseDto play(@RequestBody RequestDto requestDto) {
        String names = requestDto.getNames();
        Integer count = requestDto.getCount();

        ResponseDto responseDto = racingGameService.play(names, count, new RandomNumberGenerator());
        racingGameService.save(responseDto.getWinners(), count, responseDto.getRacingCars());

        return responseDto;
    }
}
