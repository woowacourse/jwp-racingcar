package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RaceResultDao;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.entity.RaceResultEntity;
import racingcar.service.dto.CarStatusResponse;
import racingcar.service.dto.GameInfoRequest;
import racingcar.service.dto.RaceResultResponse;
import racingcar.service.mapper.RaceResultMapper;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RaceResultService {

    private final RaceResultDao raceResultDao;
    private final CarService carService;
    private final NumberGenerator numberGenerator;
    private final RaceResultMapper raceResultMapper;

    public RaceResultService(final RaceResultDao raceResultDao, final CarService carService,
                             final NumberGenerator numberGenerator, final RaceResultMapper raceResultMapper) {
        this.raceResultDao = raceResultDao;
        this.carService = carService;
        this.numberGenerator = numberGenerator;
        this.raceResultMapper = raceResultMapper;
    }

    @Transactional
    public RaceResultResponse createRaceResult(final GameInfoRequest gameInfoRequest) {

        final RacingGame racingGame = RacingGame.readyToRacingGame(
                gameInfoRequest.getNames(),
                numberGenerator,
                gameInfoRequest.getCount()
        );
        racingGame.play();

        final RaceResultEntity raceResultEntity = raceResultMapper.mapToRaceResultEntity(racingGame);
        final int savedId = raceResultDao.save(raceResultEntity);
        carService.registerCars(racingGame, savedId);

        final List<CarStatusResponse> carStatusResponses =
                raceResultMapper.mapToCarStatusResponseFrom(racingGame);

        return new RaceResultResponse(raceResultEntity.getWinners(), carStatusResponses);
    }

    @Transactional(readOnly = true)
    public List<RaceResultResponse> searchRaceResult() {

        final Map<String, List<CarEntity>> allRaceResult = raceResultDao.findAllRaceResult();

        return allRaceResult.entrySet()
                            .stream()
                            .map(it -> new RaceResultResponse(
                                    it.getKey(),
                                    raceResultMapper.mapToCarStatusResponseFrom(it.getValue()))
                            )
                            .collect(Collectors.toList());
    }
}
