package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.ServiceControllerDto;
import racingcar.service.WebService;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/plays")
    public ResponseEntity getWinners(){

        return ResponseEntity.ok()
                .body(getGameLogResponseDtos());
    }

    private List<ResponseDto> getGameLogResponseDtos() {
        return webService.mappingEachGame()
                .stream()
                .map(dto->new ResponseDto(dto.getWinners(),dto.getGameLog()))
                .collect(Collectors.toList());
    }

}
