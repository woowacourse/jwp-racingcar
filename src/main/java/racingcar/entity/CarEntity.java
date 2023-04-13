package racingcar.entity;

public class CarEntity {

    private int id;
    private final String name;
    private final int position;

    public CarEntity(int id, String name, int position) {
        this.id = id;
        this.name = name;
        this.position = position;
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

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {

        private int id;
        private String name;
        private int position;

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

        public CarEntity build() {
            return new CarEntity(id, name, position);
        }

    }

}
