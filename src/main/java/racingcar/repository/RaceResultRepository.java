package racingcar.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import racingcar.dao.raceresult.RaceResultDao;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.RacingCars;

@Repository
public class RaceResultRepository {

    private final RaceResultDao raceResultDao;

    public RaceResultRepository(RaceResultDao raceResultDao) {
        this.raceResultDao = raceResultDao;
    }

    public int save(int trialCount, RacingCars racingCars) {
        return raceResultDao.save(RaceResultRegisterRequest.create(trialCount, racingCars));
    }

    public String findWinnersByPlayResultId(int playResultId) {
        return raceResultDao.findWinnersByPlayResultId(playResultId);
    }

    public List<Integer> findAllPlayResultId() {
        return raceResultDao.findAllPlayResultId();
    }
}
