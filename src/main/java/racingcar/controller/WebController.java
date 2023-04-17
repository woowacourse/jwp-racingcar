package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameDto;
import racingcar.dto.RecordDto;
import racingcar.model.Cars;
import racingcar.service.RacingGameService;
import racingcar.service.GameRecordService;

import java.util.List;

@RestController
public class WebController {

    private final RacingGameService racingGameService;
    private final GameRecordService gameRecordService;

    public WebController(final RacingGameService racingGameService, final GameRecordService gameRecordService) {
        this.racingGameService = racingGameService;
        this.gameRecordService = gameRecordService;
    }

    @PostMapping("/plays")
    public RecordDto plays(@RequestBody final GameDto gameDto) {
        Cars cars = racingGameService.play(gameDto);

        gameRecordService.plays(gameDto.getCount(), cars);

        return new RecordDto(cars.getWinner(), racingGameService.carsToDto(cars));
    }

    @GetMapping("/plays")
    public List<RecordDto> record() {
        return gameRecordService.findRecords();
    }
}
