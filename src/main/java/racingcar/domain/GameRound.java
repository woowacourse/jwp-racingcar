package racingcar.domain;

import racingcar.dto.GameRoundRequest;

public class GameRound {
    private final int totalRound;
    private int currRound;

    public GameRound(int totalRound) {
        this.totalRound = totalRound;
        this.currRound = 0;
    }

    public GameRound from(GameRoundRequest inputGameRound) {
        int totalRound = inputGameRound.getRound();
        return new GameRound(totalRound);
    }

    public void increaseRound() {
        this.currRound++;
    }

    public boolean isEnd() {
        return this.currRound == this.totalRound;
    }
}
