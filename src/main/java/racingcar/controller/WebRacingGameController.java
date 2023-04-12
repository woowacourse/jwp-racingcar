package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.JdbcTemplateDAO;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.dto.CarDto;
import racingcar.dto.GameInfoDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameResultResponseDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 2023-04-12 Service 계층으로 비즈니스 로직 분리
@RestController
public class WebRacingGameController {
    private final JdbcTemplateDAO jdbcTemplateDAO;

    public WebRacingGameController(JdbcTemplateDAO jdbcTemplateDAO) {
        this.jdbcTemplateDAO = jdbcTemplateDAO;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponseDto> playGame(@RequestBody GameInfoDto request) {
        List<Name> names = Arrays.stream(request.getNames().split(","))
                .map(Name::new)
                .collect(Collectors.toList());

        RacingGame racingGame = new RacingGame(names);
        TryCount trycount = new TryCount(request.getCount());

        for (int i = 0; i < trycount.getCount(); i++) {
            racingGame.moveCars();
        }

        String winners = racingGame.decideWinners().getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));

        List<CarDto> carDtoList = new ArrayList<>();

        for (Car car : racingGame.getCars()) {
            carDtoList.add(new CarDto(car.getName(), car.getPosition()));
        }

        int savedId = jdbcTemplateDAO.saveResult(new GameResultDto(trycount.getCount(), winners, carDtoList));

        if (savedId < 1) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(new GameResultResponseDto(winners, carDtoList));
    }
}
