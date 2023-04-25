package racingcar.entity;

import java.util.List;

public class RacingGameEntity {

    private final GameEntity gameEntity;
    private final List<PlayerEntity> playerEntity;

    public RacingGameEntity(final GameEntity gameEntity, final List<PlayerEntity> playerEntity) {
        this.gameEntity = gameEntity;
        this.playerEntity = playerEntity;
    }

    public GameEntity getGame() {
        return gameEntity;
    }

    public List<PlayerEntity> getPlayer() {
        return playerEntity;
    }

    @Override
    public String toString() {
        return "RacingGameEntity{" +
                "game=" + gameEntity +
                ", player=" + playerEntity +
                '}';
    }
}
