package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.WebService;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;

@RestController
public class WebController {
    private final WebService webService;
    public WebController(final WebService webService){
        this.webService = webService;
    }
    @PostMapping("/plays")
    public ResponseDto postInput(@RequestBody RequestDto requestDto) {
        webService.setUpGame(requestDto.getNames());
        webService.play(requestDto.getCount());
        ResponseDto responseDto = new ResponseDto(webService.findWinners(), webService.getCars());
        return responseDto;
    }
}
