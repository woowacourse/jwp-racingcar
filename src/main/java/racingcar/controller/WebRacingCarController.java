package racingcar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.NamesAndCountDto;
import racingcar.dto.WinnersAndCarsDto;
import racingcar.service.MainRacingCarService;

@RestController
@RequestMapping("/plays")
public class WebRacingCarController {

    private final MainRacingCarService mainRacingCarService;

    public WebRacingCarController(final MainRacingCarService mainRacingCarService) {
        this.mainRacingCarService = mainRacingCarService;
    }

    @GetMapping
    public ResponseEntity<List<WinnersAndCarsDto>> findAllRecords() {
        final List<WinnersAndCarsDto> records = mainRacingCarService.findAllRecords();
        return ResponseEntity.ok().body(records);
    }

    @PostMapping
    public ResponseEntity<WinnersAndCarsDto> playRacingCar(@RequestBody NamesAndCountDto namesAndCountDto) {
        final WinnersAndCarsDto winnersAndCarsDto = mainRacingCarService.raceCar(namesAndCountDto);
        return ResponseEntity.ok().body(winnersAndCarsDto);
    }
}
