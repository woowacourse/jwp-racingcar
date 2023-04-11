package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.response.CarDto;
import racingcar.dto.response.CarGameResponse;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    public CarGameResponse play(RacingGame game) {
        progress(game);
        List<CarDto> carDtos = game.getCars().getUnmodifiableCars().stream().map(CarDto::new).collect(Collectors.toList());
        String winners = game.decideWinners().stream().collect(Collectors.joining(","));
        CarGameResponse response = new CarGameResponse(winners, carDtos);
        return response;
    }

    private static void progress(RacingGame game) {
        while (!game.isEnd()) {
            game.play();
        }
    }
}
