package racingcar.entity;

public class CarEntity {

    private int id;
    private final String name;
    private final int position;
    private final int racingGameId;

    public CarEntity(int id, String name, int position, int racingGameId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.racingGameId = racingGameId;
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

    public int getRacingGameId() {
        return racingGameId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {

        private int id;
        private String name;
        private int position;
        private int racingGameId;

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

        public Builder racingGameId(int racingGameId) {
            this.racingGameId = racingGameId;
            return this;
        }

        public CarEntity build() {
            return new CarEntity(id, name, position, racingGameId);
        }

    }

}
