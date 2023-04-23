package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.controller.dto.RacingGameResultDto;
import racingcar.service.RacingCarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    @Autowired
    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponseDto>> getGameHistory() {
        return ResponseEntity.ok()
                .body(racingCarService.getGameHistory());
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDtoForPlays gameRequestDtoForPlays, HttpServletResponse response) throws IOException {
        RacingGameResultDto racingGameResultDto = racingCarService.plays(gameRequestDtoForPlays);
        int savedGameId = racingCarService.saveGameResult(racingGameResultDto);
        response.sendRedirect("/gameResult?savedGameId=" + savedGameId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/gameResult")
    public ResponseEntity<GameResponseDto> gameResult(@RequestParam int savedGameId) {
        return ResponseEntity.ok()
                .body(racingCarService.getSavedGameById(savedGameId));
    }

}
