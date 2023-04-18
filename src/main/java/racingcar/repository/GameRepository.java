package racingcar.repository;

import racingcar.dto.GameResultResponse;

import java.util.List;

public interface GameRepository {

    void saveGameRecord(final GameResultResponse gameResultResponse, final int trialCount);

    List<GameResultResponse> makeGameRecords();
}
