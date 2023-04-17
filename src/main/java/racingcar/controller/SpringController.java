package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Cars;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.service.SpringService;

@RestController
public class SpringController {

    private final SpringService springService;

    public SpringController(SpringService springService) {
        this.springService = springService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResponseDto> postInput(@RequestBody RequestDto requestDto) {
        Cars cars = Cars.of(requestDto.getNames());
        ResponseDto responseDto = springService.play(cars, requestDto.getCount());
        return ResponseEntity.ok()
                .body(responseDto);
    }
}
