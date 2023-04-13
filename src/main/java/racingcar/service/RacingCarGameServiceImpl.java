package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.RacingCarGameDao;

@Service
public class RacingCarGameServiceImpl implements RacingCarGameService {

    private final RacingCarGameDao racingCarGameDao;

    @Autowired
    public RacingCarGameServiceImpl(final RacingCarGameDao racingCarGameDao) {
        this.racingCarGameDao = racingCarGameDao;
    }
}
