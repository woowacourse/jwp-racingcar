package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarsDao;
import racingcar.dao.PlayRecordsDao;
import racingcar.domain.RacingGame;
import racingcar.dto.JudgedCarDto;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.PlayResponseDtoConverter;

@Service
@Transactional(readOnly = true)
public class RacingCarService {

    private final PlayRecordsDao gameDao;
    private final CarsDao carsDao;
    private final RacingGame racingGame;

    public RacingCarService(final PlayRecordsDao gameDao, final CarsDao carsDao, final RacingGame racingGame) {
        this.gameDao = gameDao;
        this.carsDao = carsDao;
        this.racingGame = racingGame;
    }

    @Transactional
    public PlayResponseDto playGame(final int count, final List<String> carNames) {
        final List<JudgedCarDto> judgedCars = racingGame.play(count, carNames);
        saveGame(count, judgedCars);
        return PlayResponseDtoConverter.from(judgedCars);
    }

    private void saveGame(final int count, final List<JudgedCarDto> judgedCars) {
        final long savedId = gameDao.insertAndReturnId(count);
        carsDao.insert(savedId, judgedCars);
    }

    public List<PlayResponseDto> findAllPlayRecords() {
        final Map<Long, List<JudgedCarDto>> judgedCarsByPlayId = carsDao.findAllCarsByPlayId();
        return judgedCarsByPlayId.values()
                .stream()
                .map(PlayResponseDtoConverter::from)
                .collect(Collectors.toList());
    }

}
