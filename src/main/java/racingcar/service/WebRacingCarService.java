package racingcar.service;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.CarEntity;
import racingcar.dto.RacingGameEntity;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.repository.RacingCarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebRacingCarService implements RacingCarService {

    private final RacingCarRepository racingCarRepository;
    //TODO: Spring bean으로 관리
    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    public WebRacingCarService(RacingCarRepository racingCarRepository) {
        this.racingCarRepository = racingCarRepository;
    }

    @Override
    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        final RacingGame racingGame = racingGameRequest.toEntity();
        racingGame.play(numberGenerator);
        racingCarRepository.save(createRacingGameEntity(racingGame));

        return RacingGameResponse.createByCars(racingGame.getCars(), racingGame.findWinnerCars());
    }

    private RacingGameEntity createRacingGameEntity(RacingGame racingGame) {
        List<Car> winnerCars = racingGame.findWinnerCars();

        final List<CarEntity> carEntities = racingGame.getCars().stream()
                .map(car -> new CarEntity(car.getName(), car.getPosition(), winnerCars.contains(car)))
                .collect(toList());

        return new RacingGameEntity(carEntities,racingGame.getTotalRound());
    }

    @Override
    public List<RacingGameResponse> findGameResults() {
        final List<CarEntity> carEntities = racingCarRepository.findAll();

        final Map<Integer, List<CarEntity>> carEntitiesByGameId = carEntities.stream()
                .collect(groupingBy(CarEntity::getGameId));

        return carEntitiesByGameId.values().stream()
                .map(RacingGameResponse::createByEntity)
                .collect(toList());
    }
}
