package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.service.Service;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private final Service service;
    public Controller(final Service service){
        this.service = service;
    }
    @PostMapping("/plays")
    public ResponseDto postInput(@RequestBody RequestDto requestDto) {
        service.setUpGame(requestDto.getNames());
        service.play(requestDto.getCount());
        ResponseDto responseDto = new ResponseDto(service.findWinners(), service.getCars());
        return responseDto;
    }

    @GetMapping("/plays")
    public ResponseEntity getWinners(){

        return ResponseEntity.ok()
                .body(getGameLogResponseDtos());
    }

    private List<ResponseDto> getGameLogResponseDtos() {
        return service.mappingEachGame()
                .stream()
                .map(dto->new ResponseDto(dto.getWinners(),dto.getGameLog()))
                .collect(Collectors.toList());
    }
}
