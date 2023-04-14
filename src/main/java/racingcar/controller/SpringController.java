package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.SpringService;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;

@RestController
public class SpringController {
    private final SpringService springService;
    public SpringController(final SpringService springService){
        this.springService =springService;
    }
    @PostMapping("/plays")
    public ResponseDto postInput(@RequestBody RequestDto requestDto) {
        springService.setUpGame(requestDto.getNames());
        springService.play(requestDto.getCount());
        ResponseDto responseDto = new ResponseDto(springService.findWinners(), springService.getCars());
        return responseDto;
    }
}
