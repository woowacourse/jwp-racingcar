package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.raceresult.RaceResultDao;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;

@Service
public class RaceResultService {

    public RaceResultService(RaceResultDao raceResultDao) {
        this.raceResultDao = raceResultDao;
    }

    private final RaceResultDao raceResultDao;

    public int generate(RaceResultRegisterRequest raceResultRegisterRequest) {
        return raceResultDao.save(raceResultRegisterRequest);
    }

    public String searchWinners(int playResultId) {
        return raceResultDao.findWinnersByPlayResultId(playResultId);
    }
}

