package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.response.CarGameResponse;
import racingcar.dto.response.CarResponse;
import racingcar.entity.CarResultEntity;
import racingcar.entity.PlayResultEntity;
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

        saveCarResult(tryCount, cars, winners);
        List<CarResponse> carResponses = getCarResponses(cars);
        
        return new CarGameResponse(winners, carResponses);
    }

    private static List<CarResponse> getCarResponses(Cars cars) {
        return cars.getUnmodifiableCars()
                .stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
    }

    private void saveCarResult(int tryCount, Cars cars, String winners) {
        long resultId = playResultMapper.save(PlayResultEntity.of(tryCount, winners));
        cars.getUnmodifiableCars()
                .stream()
                .map(car -> CarResultEntity.of(resultId, car.getName(), car.getPosition()))
                .forEach(carResultMapper::save);
    }

    private void progress(RacingGame game) {
        while (!game.isEnd()) {
            game.play();
        }
    }
}
