package racingcar.entity;

public class RacingCarEntity {

    private int id;
    private String name;
    private int position;
    private int racingGameId;

    public RacingCarEntity(int id, String name, int position, int racingGameId) {
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

        public RacingCarEntity build() {
            return new RacingCarEntity(id, name, position, racingGameId);
        }

    }

}
