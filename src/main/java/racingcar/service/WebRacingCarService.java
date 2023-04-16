package racingcar.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.entity.CarEntity;
import racingcar.domain.entity.RacingGameEntity;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.repository.RacingCarRepository;

@Service
public class WebRacingCarService {

    private final RacingCarRepository racingCarRepository;

    public WebRacingCarService(final RacingCarRepository racingCarRepository) {
        this.racingCarRepository = racingCarRepository;
    }

    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        final RacingGame racingGame = racingGameRequest.toEntity();
        racingGame.play();
        racingCarRepository.save(createRacingGameEntity(racingGame));

        return RacingGameResponse.createByCars(racingGame.getCars(), racingGame.findWinnerCars());
    }

    private RacingGameEntity createRacingGameEntity(RacingGame racingGame) {
        List<Car> winnerCars = racingGame.findWinnerCars();

        final List<CarEntity> carEntities = racingGame.getCars().stream()
                .map(car -> new CarEntity(car.getName(), car.getPosition(), winnerCars.contains(car)))
                .collect(toList());

        return new RacingGameEntity(carEntities, racingGame.getTotalRound());
    }

    public List<RacingGameResponse> findGameResults() {
        final List<RacingGameEntity> racingGameEntities = racingCarRepository.findAll();

        return racingGameEntities.stream()
                .map(RacingGameEntity::getCarEntities)
                .map(RacingGameResponse::createByEntity)
                .collect(toList());
    }
}
