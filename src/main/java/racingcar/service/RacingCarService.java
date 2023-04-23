package racingcar.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.entity.CarEntity;
import racingcar.domain.entity.RacingGameEntity;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.repository.RacingCarRepository;

@Service
public class RacingCarService {

    private final RacingCarRepository racingCarRepository;
    private final NumberGenerator numberGenerator;

    public RacingCarService(final RacingCarRepository racingCarRepository, final NumberGenerator numberGenerator) {
        this.racingCarRepository = racingCarRepository;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public RacingGameResponse play(final RacingGameRequest racingGameRequest) {
        final RacingGame racingGame = racingGameRequest.toEntity();
        racingGame.play(numberGenerator);
        racingCarRepository.save(createRacingGameEntity(racingGame));

        return RacingGameResponse.createByCars(racingGame.getCars(), racingGame.findWinnerCars());
    }

    private RacingGameEntity createRacingGameEntity(final RacingGame racingGame) {
        List<Car> winnerCars = racingGame.findWinnerCars();

        final List<CarEntity> carEntities = racingGame.getCars().stream()
                .map(car -> new CarEntity(car.getName(), car.getPosition(), winnerCars.contains(car)))
                .collect(toList());

        return new RacingGameEntity(carEntities, racingGame.getTotalRound());
    }

    @Transactional(readOnly = true)
    public List<RacingGameResponse> findGameResults() {
        final List<RacingGameEntity> racingGameEntities = racingCarRepository.findAll();

        return racingGameEntities.stream()
                .map(RacingGameEntity::getCarEntities)
                .map(RacingGameResponse::createByEntity)
                .collect(toList());
    }
}
