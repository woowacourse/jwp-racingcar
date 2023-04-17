package racingcar.entity;

public class CarEntity {

    private int id;
    private final String name;
    private final int position;
    private final int gameId;

    public CarEntity(int id, String name, int position, int gameId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getGameId() {
        return gameId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {

        private int id;
        private String name;
        private int position;
        private int gameId;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder position(int position) {
            this.position = position;
            return this;
        }

        public Builder gameId(int gameId) {
            this.gameId = gameId;
            return this;
        }

        public CarEntity build() {
            return new CarEntity(id, name, position, gameId);
        }

    }

}
