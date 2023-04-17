package racingcar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.service.MainRacingCarService;

@Controller
@RequestMapping("/plays")
public class RacingCarWebController {

    private final MainRacingCarService mainRacingCarService;

    public RacingCarWebController(final MainRacingCarService mainRacingCarService) {
        this.mainRacingCarService = mainRacingCarService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> findAllRecords() {
        final List<ResponseDto> records = mainRacingCarService.findAllRecords();
        return ResponseEntity.ok().body(records);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseDto> playRacingCar(@RequestBody RequestDto requestDto) {
        final ResponseDto responseDto = mainRacingCarService.raceCar(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }
}
