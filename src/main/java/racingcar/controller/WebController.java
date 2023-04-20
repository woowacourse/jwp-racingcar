package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.*;
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
    public ResponseEntity<PlayResponseDto> play(@RequestBody PlayRequestDto playRequestDto) {
        List<String> names = ValueEditor.splitByComma(playRequestDto.getNames());
        String count = playRequestDto.getCount();
        RacingGameDto racingGameDto = racingGameService.play(new StartInformationDto(names, count));
        PlayResponseDto playResponseDto = RacingGameMapper.toResponseDto(racingGameDto);
        return ResponseEntity.ok().body(playResponseDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlayResponseDto>> queryHistory() {
        List<RacingGameDto> racingGameDtos = racingGameService.queryHistory();
        List<PlayResponseDto> playResponseDtos = RacingGameMapper.toResponseDtos(racingGameDtos);
        return ResponseEntity.ok().body(playResponseDtos);
    }

}
