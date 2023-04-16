package racingcar.domain;

import racingcar.domain.engine.Engine;

public class Car {
    private static final int INITIAL_POSITION = 0;

    private final Name name;
    private Position position;
    private final Engine engine;

    public Car(Name name, Engine engine) {
        this.name = name;
        this.position = Position.from(INITIAL_POSITION);
        this.engine = engine;
    }

    public Car(Name name, int position, Engine engine) {
        this.name = name;
        this.position = Position.from(position);
        this.engine = engine;
    }

    public void tryMove() {
        if (engine.isMovable()) {
            this.position = position.moveForward();
        }
    }

    public int getPosition() {
        return position.getPosition();
    }

    public String getName() {
        return name.getName();
    }
}
