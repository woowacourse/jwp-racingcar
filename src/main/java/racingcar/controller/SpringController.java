package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RandomMoveChance;
import racingcar.domain.SpringService;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;

@RestController
@RequestMapping("/plays")
public class SpringController {
    @PostMapping("")
    public ResponseEntity postInput(@RequestBody RequestDto requestDto) {
        SpringService springService = new SpringService(requestDto, new RandomMoveChance());
        springService.play();
        ResponseDto responseDto = new ResponseDto(springService.findWinners(), springService.getCars());
        return ResponseEntity.ok()
                .body(responseDto);
    }
}
