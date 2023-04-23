package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.dto.RacingGameResponse;

@Service
public class RacingGameFindService {
    private final CarDao carDao;
    private final RacingGameDao racingGameDao;

    public RacingGameFindService(CarDao carDao, RacingGameDao racingGameDao) {
        this.carDao = carDao;
        this.racingGameDao = racingGameDao;
    }

    public List<RacingGameResponse> findHistory() {
        List<RacingGameEntity> racingGameEntities = racingGameDao.findAllByCreatedTimeAsc();

        return racingGameEntities.stream()
                .map(RacingGameEntity::getId)
                .map(carDao::findByRacingGameId)
                .map(RacingGameResponse::of)
                .collect(Collectors.toList());
    }
}
