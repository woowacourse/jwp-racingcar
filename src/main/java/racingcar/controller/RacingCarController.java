package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.controller.dto.RacingGameResultDto;
import racingcar.service.WebService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class RacingCarController {

    private final WebService webService;

    @Autowired
    public RacingCarController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponseDto>> getGameHistory() {
        return ResponseEntity.ok()
                .body(webService.getGameHistory());
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDtoForPlays gameRequestDtoForPlays, HttpServletResponse response) throws IOException {
        RacingGameResultDto racingGameResultDto = webService.plays(gameRequestDtoForPlays);
        int savedGameId = webService.saveGameResult(racingGameResultDto);
        response.sendRedirect("/gameResult?savedGameId=" + savedGameId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/gameResult")
    public ResponseEntity<GameResponseDto> gameResult(@RequestParam int savedGameId) {
        return ResponseEntity.ok()
                .body(webService.getSavedGameById(savedGameId));
    }

}
