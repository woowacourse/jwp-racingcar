package racingcar.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.entity.CarResultEntity;
import racingcar.domain.entity.RacingGameResultEntity;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.repository.RacingCarRepository;

@Service
public class RacingCarService {

    private final RacingCarRepository racingCarRepository;
    private final NumberGenerator numberGenerator;

    public RacingCarService(RacingCarRepository racingCarRepository, NumberGenerator numberGenerator) {
        this.racingCarRepository = racingCarRepository;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        RacingGame racingGame = racingGameRequest.toEntity();
        racingGame.play(numberGenerator);
        racingCarRepository.save(createRacingGameEntity(racingGame));

        return RacingGameResponse.createByCars(racingGame.getCars(), racingGame.findWinnerCars());
    }

    private RacingGameResultEntity createRacingGameEntity(RacingGame racingGame) {
        List<Car> winnerCars = racingGame.findWinnerCars();

        List<CarResultEntity> carEntities = racingGame.getCars().stream()
                .map(car -> new CarResultEntity(car.getName(), car.getPosition(), winnerCars.contains(car)))
                .collect(toList());

        return new RacingGameResultEntity(carEntities, racingGame.getTotalRound());
    }

    @Transactional(readOnly = true)
    public List<RacingGameResponse> findGameResults() {
        List<RacingGameResultEntity> racingGameEntities = racingCarRepository.findAll();

        return racingGameEntities.stream()
                .map(RacingGameResultEntity::getCarEntities)
                .map(RacingGameResponse::createByEntity)
                .collect(toList());
    }
}
