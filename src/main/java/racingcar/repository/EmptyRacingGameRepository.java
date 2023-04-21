package racingcar.repository;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.entity.GameHistoryEntity;

public class EmptyRacingGameRepository implements RacingGameRepository {

    private static final String CALL_UNSUPPORTED_OPERATION_EXCEPTION = "[ERROR] 지원하지 않는 기능입니다.";

    @Override
    public List<GameHistoryEntity> findGameHistories() {
        throw new UnsupportedOperationException(CALL_UNSUPPORTED_OPERATION_EXCEPTION);
    }

    @Override
    public void saveGameResult(final Cars cars) {
        throw new UnsupportedOperationException(CALL_UNSUPPORTED_OPERATION_EXCEPTION);
    }
}
