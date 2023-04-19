package racingcar.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingCarRecord;
import racingcar.dao.RacingCarRecordDao;
import racingcar.dao.RacingGameHistory;
import racingcar.dao.RacingGameHistoryDao;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.NumberGenerator;
import racingcar.domain.game.RacingGame;
import racingcar.dto.RacingGameDto;

@Service
public class RacingGameService {

    private final RacingGameHistoryDao racingGameHistoryDao;
    private final RacingCarRecordDao racingCarRecordDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(RacingGameHistoryDao racingGameHistoryDao, RacingCarRecordDao racingCarRecordDao,
                             NumberGenerator numberGenerator) {
        this.racingGameHistoryDao = racingGameHistoryDao;
        this.racingCarRecordDao = racingCarRecordDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameDto play(int trialCount, List<String> names) {
        RacingGame game = createGame(trialCount, names);
        game.play(trialCount, numberGenerator);
        insertCars(game);
        return RacingGameDto.from(game);
    }

    private RacingGame createGame(int trialCount, List<String> names) {
        RacingGame game = RacingGame.from(names);
        Long historyId = racingGameHistoryDao.insert(trialCount, LocalDateTime.now());
        game.setId(historyId);
        return game;
    }

    private void insertCars(RacingGame game) {
        for (RacingCar racingCar : game.getRacingCars()) {
            Long savedId = racingCarRecordDao.insert(game.getId(), racingCar, game.isWinner(racingCar));
            racingCar.setId(savedId);
        }
    }

    public List<RacingGameDto> readGameHistory() {
        List<RacingGameHistory> racingGameHistories = racingGameHistoryDao.selectAll();
        return racingGameHistories.stream()
                .mapToLong(history -> history.getId())
                .mapToObj(this::findById)
                .map(RacingGameDto::from)
                .collect(Collectors.toList());

    }
    
    private RacingGame findById(Long id) {
        List<RacingCar> racingCars = racingCarRecordDao.findByHistoryId(id)
                .stream().map(RacingCarRecord::toDomain)
                .collect(Collectors.toList());
        return new RacingGame(id, racingCars);
    }
}
