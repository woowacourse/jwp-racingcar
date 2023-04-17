package racingcar.domain;

import racingcar.domain.engine.Engine;

public class Car {
    private final Name name;
    private final Position position;
    private final Engine engine;

    private Car(Name name, Position position, Engine engine) {
        this.name = name;
        this.position = position;
        this.engine = engine;
    }

    public static Car of(Name name, Engine engine) {
        return new Car(name, Position.create(), engine);
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
