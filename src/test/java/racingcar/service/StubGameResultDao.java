package racingcar.service;

import racingcar.dao.GameResultDao;
import racingcar.dto.GameResultDto;

class StubGameResultDao implements GameResultDao {
    @Override
    public int save(GameResultDto resultDto) {
        return 1;
    }
}
