package racingcar.dao;

import java.time.LocalTime;

public class GameAndPlayerResultEntity {
    private int gameId;
    private int trialCount;
    private LocalTime createdAt;
    private int playerId;
    private String name;
    private int position;

    public GameAndPlayerResultEntity() {

    }

    private GameAndPlayerResultEntity(int gameId, int trialCount, LocalTime createdAt,
                                      int playerId, String name, int position) {
        this.gameId = gameId;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
        this.playerId = playerId;
        this.name = name;
        this.position = position;
    }

    public static class GameAndPlayerResultEntityBuilder {
        private int gameId;
        private int trialCount;
        private LocalTime createdAt;
        private int playerId;
        private String name;
        private int position;

        public GameAndPlayerResultEntityBuilder gameId(int gameId) {
            this.gameId = gameId;
            return this;
        }

        public GameAndPlayerResultEntityBuilder trialCount(int trialCount) {
            this.trialCount = trialCount;
            return this;
        }

        public GameAndPlayerResultEntityBuilder createdAt(LocalTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public GameAndPlayerResultEntityBuilder playerId(int playerId) {
            this.playerId = playerId;
            return this;
        }

        public GameAndPlayerResultEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GameAndPlayerResultEntityBuilder position(int position) {
            this.position = position;
            return this;
        }

        public GameAndPlayerResultEntity build() {
            return new GameAndPlayerResultEntity(gameId, trialCount, createdAt, playerId, name, position);
        }
    }

    public int getGameId() {
        return gameId;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
