package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.service.MainRacingCarService;

@RestController
public class RacingCarWebController {

    private final MainRacingCarService mainRacingCarService;

    @Autowired
    public RacingCarWebController(final MainRacingCarService mainRacingCarService) {
        this.mainRacingCarService = mainRacingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResponseDto> playRacingCar(@RequestBody RequestDto requestDto) {
        final ResponseDto responseDto = mainRacingCarService.raceCar(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }
}
