package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInformationDto;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.services.RacingGameService;
import racingcar.util.ValueEditor;

import java.util.List;

@RestController
public class WebController {

    private final RacingGameService racingGameService;

    public WebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResponseDto> play(@RequestBody RequestDto requestDto) {
        List<String> names = ValueEditor.splitByComma(requestDto.getNames());
        String count = requestDto.getCount();
        ResponseDto responseDto = racingGameService.play(new GameInformationDto(names, count));
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResponseDto>> queryHistory() {
        List<ResponseDto> responseDto = racingGameService.queryHistory();
        return ResponseEntity.ok().body(responseDto);
    }

}
