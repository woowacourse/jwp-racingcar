package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarResultDao;
import racingcar.dao.PlayResultDao;
import racingcar.domain.*;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.dto.response.CarResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private final CarResultDao carResultDao;
    private final PlayResultDao playResultDao;

    public RacingGameService(CarResultDao carResultDao, PlayResultDao playResultDao) {
        this.carResultDao = carResultDao;
        this.playResultDao = playResultDao;
    }

    public CarGameResponse play(CarGameRequest carGameRequest) {
        RacingGame racingGame = createRacingGame(carGameRequest);
        racingGame.play();
        saveResult(racingGame, carGameRequest.getCount());
        return CarGameResponse.of(racingGame.getWinnerNames(), racingGame.getCars());
    }

    private RacingGame createRacingGame(CarGameRequest carGameRequest) {
        Cars cars = Cars.from(carGameRequest.getNames());
        return new RacingGame(new CarRandomNumberGenerator(), cars, carGameRequest.getCount());
    }

    private void saveResult(RacingGame racingGame, int tryCount) {
        int playResultId = savePlayResult(tryCount, racingGame, racingGame.getWinnerNames());
        saveCarResult(racingGame.getCars(), playResultId);
    }

    private int savePlayResult(int tryCount, RacingGame racingGame, String winners) {
        return playResultDao.save(PlayResult.of(tryCount, winners, racingGame.getCreatedAt()));
    }

    private void saveCarResult(List<Car> cars, int playResultId) {
        cars.stream()
                .map(car -> CarResult.of(playResultId, car.getName(), car.getPosition()))
                .forEach(carResultDao::save);
    }

    public List<CarGameResponse> findAllCarGame() {
        List<CarGameResponse> carGameResponses = new ArrayList<>();
        for (PlayResult playResult : playResultDao.findAll()) {
            List<CarResult> carResults = carResultDao.findAllByPlayResultId(playResult.getId());
            List<CarResponse> carResponses = carResults.stream()
                    .map(CarResponse::fromCarResult)
                    .collect(Collectors.toList());
            carGameResponses.add(new CarGameResponse(playResult.getWinners(), carResponses));
        }
        return carGameResponses;
    }
}
