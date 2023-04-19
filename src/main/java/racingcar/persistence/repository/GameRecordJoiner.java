package racingcar.persistence.repository;

import racingcar.dto.CarData;
import racingcar.dto.GameResultResponse;
import racingcar.persistence.entity.GameResultEntity;
import racingcar.persistence.entity.PlayerResultEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GameRecordJoiner {

    public List<GameResultResponse> join(
            final List<GameResultEntity> gameResults,
            final List<PlayerResultEntity> playerResults
    ) {
        return gameResults.stream()
                .map(gameResult -> new GameResultResponse(
                        gameResult.getWinners(),
                        collectCarData(playerResults, gameResult.getId())
                )).collect(Collectors.toList());
    }

    private List<CarData> collectCarData(
            final List<PlayerResultEntity> playerResults,
            final int gameResultId
    ) {
        return playerResults.stream()
                .filter(playerResultEntity -> playerResultEntity.getGameResultId() == gameResultId)
                .map(playerResult -> new CarData(playerResult.getName(), playerResult.getPosition()))
                .collect(Collectors.toList());
    }
}
