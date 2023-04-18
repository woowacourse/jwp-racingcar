package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.RandomNumberGenerator;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.dto.response.CarResponse;
import racingcar.entity.CarResultEntity;
import racingcar.entity.PlayResultEntity;
import racingcar.mapper.CarResultMapper;
import racingcar.mapper.PlayResultMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final CarResultMapper h2CarResultMapper;
    private final PlayResultMapper h2PlayResultMapper;
    private final RandomNumberGenerator numberGenerator;

    public RacingGameService(CarResultMapper h2CarResultMapper, PlayResultMapper h2PlayResultMapper, RandomNumberGenerator numberGenerator) {
        this.h2CarResultMapper = h2CarResultMapper;
        this.h2PlayResultMapper = h2PlayResultMapper;
        this.numberGenerator = numberGenerator;
    }

    public CarGameResponse play(CarGameRequest request) {

        List<String> names = Arrays.stream(request.getNames().split(",", -1))
                .collect(Collectors.toList());

        RacingGame game = new RacingGame(numberGenerator, new Cars(names), request.getCount());

        int tryCount = game.getTryCount();
        progress(game);
        Cars cars = game.getCars();
        String winners = String.join(",", game.decideWinners());

        saveCarResult(tryCount, cars, winners);
        List<CarResponse> carResponses = getCarResponses(cars);

        return CarGameResponse.of(winners, carResponses);
    }

    private static List<CarResponse> getCarResponses(Cars cars) {
        return cars.getUnmodifiableCars()
                .stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
    }

    private void saveCarResult(int tryCount, Cars cars, String winners) {
        long resultId = h2PlayResultMapper.save(PlayResultEntity.of(tryCount, winners));
        cars.getUnmodifiableCars()
                .stream()
                .map(car -> CarResultEntity.of(resultId, car.getName(), car.getPosition()))
                .forEach(h2CarResultMapper::save);
    }

    private void progress(RacingGame game) {
        while (!game.isEnd()) {
            game.play();
        }
    }

    public List<CarGameResponse> findAllGame() {
        List<PlayResultEntity> entities = h2PlayResultMapper.findAll();

        return entities.stream()
                .map(playResultEntity -> CarGameResponse.of(playResultEntity.getWinners(), getAllByPlayResultId(playResultEntity.getId())))
                .collect(Collectors.toList());
    }

    private List<CarResponse> getAllByPlayResultId(Long id) {
        List<CarResultEntity> carEntities = h2CarResultMapper.findAllByPlayResultId(id);
        return carEntities.stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
    }
}
