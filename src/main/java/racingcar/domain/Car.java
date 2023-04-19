package racingcar.domain;

public class Car {
    public static final Integer DEFAULT_ID = null;
    public static final int MINIMUM_NUMBER_TO_MOVE = 4;
    private Integer carId;
    private final CarName name;
    private final Position position;
    private Integer gameId;

    public Car(Integer carId, CarName name, Position position, Integer gameId) {
        this.carId = carId;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public static Car of(String name, int position) {
        return new Car(DEFAULT_ID, new CarName(name), new Position(position), DEFAULT_ID);
    }

    public void move(NumberGenerator numberGenerator) {
        int randomNumber = numberGenerator.generate();

        if (isMovable(randomNumber)) {
            position.move();
        }
    }

    public int compareTo(Car other) {
        return this.position.getPosition() - other.position.getPosition();
    }

    public boolean isSamePosition(Car target) {
        return position.equals(target.position);
    }

    private boolean isMovable(int number) {
        return number >= MINIMUM_NUMBER_TO_MOVE;
    }

    public Integer getCarId() {
        return carId;
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

    public Integer getGameId() {
        return gameId;
    }

}
