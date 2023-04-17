package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.GameRecordResponseDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.GameService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController {

    private final GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponseDto> playGame(final @RequestBody @Valid RacingGameRequestDto racingGameRequestDto) {
        RacingGameResponseDto racingGameResponseDto = gameService.executeRacingGame(racingGameRequestDto);
        return ResponseEntity.ok(racingGameResponseDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameRecordResponseDto>> getGameRecord(){
        List<GameRecordResponseDto> gameRecordResponseDto = gameService.getGameRecord();
        return ResponseEntity.ok(gameRecordResponseDto);
    }
}
