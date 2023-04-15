package racingcar.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.CarEntity;
import racingcar.dto.RacingGameEntity;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.repository.RacingCarRepository;

@Service
public class WebRacingCarService implements RacingCarService {

    private final RacingCarRepository racingCarRepository;
    private final NumberGenerator numberGenerator;

    public WebRacingCarService(final RacingCarRepository racingCarRepository, final NumberGenerator numberGenerator) {
        this.racingCarRepository = racingCarRepository;
        this.numberGenerator = numberGenerator;
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
