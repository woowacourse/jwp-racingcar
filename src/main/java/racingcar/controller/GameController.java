package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.RandomMoveChance;
import racingcar.controller.dto.PlayRequest;
import racingcar.controller.dto.ResultResponse;
import racingcar.service.GameService;

@RestController
public class GameController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final GameService gameService;

    public GameController(JdbcTemplate jdbcTemplate) {
        this.gameService = new GameService(new GameDao(jdbcTemplate), new RandomMoveChance());
    }

    @PostMapping("/plays")
    // 매핑(직렬화)을 누가 해주는지 공부하면 좋을 것 같다.
    public ResponseEntity<ResultResponse> playGame(@RequestBody PlayRequest playRequest) {
        final List<String> names = List.of(playRequest.getNames().split(CAR_NAME_SEPARATOR));
        final List<Car> cars = makeCarsWith(trim(names));
        final int playCount = playRequest.getCount();
        System.out.println("playCount = " + playCount);

        final Game game = new Game(cars, playCount);
        gameService.play(game);

        List<String> winnerNames = getNamesOf(game.findWinners());
        List<Car> finishedCars = game.getCars();

        return ResponseEntity.ok(new ResultResponse(winnerNames, finishedCars));
    }

    // private 메서드들이 존재해도 되나?
    // 필립: 너무 구체적인 메서드들이다(상세하다).
    // 땡칠: 상관은 없을 것 같다, 컨트롤러가 이런일을 해도 될것같다.
    private List<String> getNamesOf(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    // 정팩메
    private List<Car> makeCarsWith(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private List<String> trim(List<String> carNames) {
        return carNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
