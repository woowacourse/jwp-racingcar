package racingcar.repository;

import racingcar.dto.CarData;
import racingcar.dto.GameResultResponse;
import racingcar.entity.GameResultEntity;
import racingcar.entity.PlayerResultEntity;

import java.util.ArrayList;
import java.util.List;

public class GameRecordJoiner {

    public List<GameResultResponse> join(
            final List<GameResultEntity> gameResults,
            final List<PlayerResultEntity> playerResults
    ) {
        List<GameResultResponse> gameResultResponses = new ArrayList<>();
        for (var gameResult : gameResults) {
            String winner = gameResult.getWinners();
            int gameResultId = gameResult.getId();
            List<CarData> carData = collectCarData(playerResults, gameResultId);
            gameResultResponses.add(new GameResultResponse(winner, carData));
        }
        return gameResultResponses;
    }

    private static List<CarData> collectCarData(
            final List<PlayerResultEntity> playerResults,
            final int gameResultId
    ) {
        List<CarData> carData = new ArrayList<>();
        for (var playerResult : playerResults) {
            if (playerResult.getGameResultId() == gameResultId) {
                carData.add(new CarData(playerResult.getName(), playerResult.getPosition()));
            }
        }
        return carData;
    }
}
