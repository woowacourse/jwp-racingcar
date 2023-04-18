package racingcar;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarRecordDao;
import racingcar.dao.RacingHistoryDao;
import racingcar.domain.car.Car;
import racingcar.domain.car.Winners;
import racingcar.domain.race.RacingGame;
import racingcar.dto.CarRecordDto;
import racingcar.dto.RacingHistoryDto;
import racingcar.dto.ResultDto;

@Service
public class RacingGameService {

    private final RacingHistoryDao racingHistoryDao;
    private final CarRecordDao carRecordDao;

    public RacingGameService(RacingHistoryDao racingHistoryDao, CarRecordDao carRecordDao) {
        this.racingHistoryDao = racingHistoryDao;
        this.carRecordDao = carRecordDao;
    }

    public ResultDto start(int trialCount, List<String> names) {
        RacingGame game = new RacingGame(names);
        Long historyId = racingHistoryDao.save(trialCount, LocalDateTime.now());
        game.progress(trialCount);
        saveCars(game, historyId);
        return ResultDto.of(game.getRacingCars(), game.getWinners());
    }

    private void saveCars(RacingGame game, long historyId) {
        Winners winners = game.getWinners();
        for (Car car : game.getRacingCars()) {
            carRecordDao.save(historyId, car, winners.isWinner(car));
        }
    }

    public List<ResultDto> findAllGameHistories() {
        List<RacingHistoryDto> racingHistories = racingHistoryDao.findAll();
        List<List<CarRecordDto>> carRecords = findAllCarRecordsInHistory(racingHistories);
        return carRecords.stream()
                .map(ResultDto::fromRecords)
                .collect(Collectors.toList());
    }

    private List<List<CarRecordDto>> findAllCarRecordsInHistory(List<RacingHistoryDto> racingHistories) {
        return racingHistories.stream()
                .map(history -> carRecordDao.findAllByRacingHistoryId(history.getId()))
                .collect(Collectors.toList());
    }

}
