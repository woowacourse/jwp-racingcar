package racingcar;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.race.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.ResultDto;

@RestController
public class RaceRestController {

    @PostMapping("/plays")
    public ResponseEntity<ResultDto> play(@RequestBody RacingGameRequest request) {
        // request 로 game 만들어주기
        int count = validateTryTime(request.getCount());
        RacingGame game = createGame(request);
        run2(game, count);

        return ResponseEntity.ok(new ResultDto(game.getWinners(), game.getRacingCars()));
    }

    private RacingGame createGame(RacingGameRequest request) {
        List<String> names = splitNames(request.getNames());
        return new RacingGame(names, new WinnerJudgeImpl(), request.getCount());
    }

    private List<String> splitNames(String names) {
        String regex = ",";
        return List.of(names.split(regex));
    }

    private void run2(RacingGame racingGame, int tryTime) {
        while (tryTime-- > 0) {
            racingGame.tryMoveOneTime();
        }
    }

    private int validateTryTime(int tryTime) {
        if (tryTime < 0) {
            throw new IllegalArgumentException("시도 횟수는 음수일 수 없습니다.");
        }
        return tryTime;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleTryTimeException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
