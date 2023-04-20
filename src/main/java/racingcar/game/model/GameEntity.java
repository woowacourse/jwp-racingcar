package racingcar.game.model;

import java.sql.Timestamp;

public class GameEntity {
    
    
    private final long createdAt;
    private final String winners;
    private final int count;
    private final int gameId;
    
    public GameEntity(final long createdAt, final String winners, final int count, final int gameId) {
        this.createdAt = createdAt;
        this.winners = winners;
        this.count = count;
        this.gameId = gameId;
    }
    
    public static GameEntity create(final int id, final int count, final String winners, final Timestamp createdAt) {
        return new GameEntity(createdAt.getTime(), winners, count, id);
    }
    
    public int getGameId() {
        return this.gameId;
    }
    
    public long getCreatedAt() {
        return this.createdAt;
    }
    
    public String getWinners() {
        return this.winners;
    }
}
