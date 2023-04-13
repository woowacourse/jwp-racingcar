package racingcar.domain;

import racingcar.domain.engine.Engine;

public class Car {
    private final Name name;
    private final Position position;
    private final Engine engine;

    public Car(Name name, Engine engine) {
        this.name = name;
        this.position = new Position();
        this.engine = engine;
    }

    public Car(Name name, int position, Engine engine) {
        this.name = name;
        this.position = new Position(position);
        this.engine = engine;
    }

    public void tryMove() {
        if (engine.isMovable()) {
            position.move();
        }
    }

    public int getPosition() {
        return position.getPosition();
    }

    public String getName() {
        return name.getName();
    }
}
