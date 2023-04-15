package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.PlayResultDao;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.PlayResponseDtoConverter;

@Service
public class RacingCarService {

    private final PlayResultDao playResultDao;
    private final CarDao carDao;
    private final RacingGame racingGame;

    public RacingCarService(final PlayResultDao playResultDao, final CarDao carDao, final RacingGame racingGame) {
        this.playResultDao = playResultDao;
        this.carDao = carDao;
        this.racingGame = racingGame;
    }

    @Transactional
    public PlayResponseDto playGame(int count, List<String> carNames) {
        List<CarDto> racedCars = racingGame.play(count, carNames);
        saveGame(count, racedCars);
        return PlayResponseDtoConverter.of(racedCars);
    }

    private void saveGame(int count, List<CarDto> cars) {
        long savedId = playResultDao.insertAndReturnId(count);
        carDao.insert(savedId, cars);
    }

    public List<PlayResponseDto> findAllGames() {
        Map<Long, List<CarDto>> allCars = carDao.findAllCarsById();
        return allCars.values()
                .stream()
                .map(PlayResponseDtoConverter::of)
                .collect(Collectors.toList());
    }

}
