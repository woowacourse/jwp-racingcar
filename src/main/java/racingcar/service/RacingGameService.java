package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.response.CarGameResponse;
import racingcar.dto.response.CarResponse;
import racingcar.domain.CarResult;
import racingcar.domain.PlayResult;
import racingcar.mapper.CarResultMapper;
import racingcar.mapper.PlayResultMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final CarResultMapper carResultMapper;

    private final PlayResultMapper playResultMapper;

    public RacingGameService(CarResultMapper carResultMapper, PlayResultMapper playResultMapper) {
        this.carResultMapper = carResultMapper;
        this.playResultMapper = playResultMapper;
    }

    public CarGameResponse play(RacingGame game) {
        int tryCount = game.getTryCount();
        progress(game);
        Cars cars = game.getCars();
        String winners = String.join(",", game.decideWinners());

        long playResultId = savePlayResult(tryCount, game, winners);
        saveCarResult(cars, playResultId);
        List<CarResponse> carResponses = getCarResponses(cars);
        
        return new CarGameResponse(winners, carResponses);
    }

    private static List<CarResponse> getCarResponses(Cars cars) {
        return cars.getUnmodifiableCars()
                .stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
    }

    private long savePlayResult(int tryCount, RacingGame racingGame, String winners) {
        return playResultMapper.save(PlayResult.of(tryCount, winners, racingGame.getCreatedAt()));
    }

    private void saveCarResult(Cars cars, long playResultId) {
        cars.getUnmodifiableCars()
                .stream()
                .map(car -> CarResult.of(playResultId, car.getName(), car.getPosition()))
                .forEach(carResultMapper::save);
    }

    private void progress(RacingGame game) {
        while (!game.isEnd()) {
            game.play();
        }
    }
}
