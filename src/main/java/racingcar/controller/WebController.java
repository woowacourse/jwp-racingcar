package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.*;
import racingcar.dto.view.PlayRequest;
import racingcar.dto.view.PlaySuccessResponse;
import racingcar.mapper.RacingGameMapper;
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
    public ResponseEntity<PlaySuccessResponse> play(@RequestBody PlayRequest playRequest) {
        List<String> names = ValueEditor.splitByComma(playRequest.getNames());
        RacingGameDto racingGameDto = racingGameService.play(new StartInformationDto(names, playRequest.getCount()));
        PlaySuccessResponse playSuccessResponseDto = RacingGameMapper.toResponse(racingGameDto);
        return ResponseEntity.ok().body(playSuccessResponseDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlaySuccessResponse>> queryHistory() {
        List<RacingGameDto> racingGameDtos = racingGameService.queryHistory();
        List<PlaySuccessResponse> playSuccessResponseDtos = RacingGameMapper.toResponse(racingGameDtos);
        return ResponseEntity.ok().body(playSuccessResponseDtos);
    }

}
