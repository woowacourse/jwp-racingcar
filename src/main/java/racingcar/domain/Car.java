package racingcar.domain;

import racingcar.domain.engine.Engine;

public class Car {
    private final Name name;
    private final Position position;

    private Car(Name name, Position position) {
        this.name = name;
        this.position = position;
    }

    public static Car from(Name name) {
        return new Car(name, Position.create());
    }

    public static Car of(Name name, Position position) {
        return new Car(name, position);
    }

    public void tryMove(Engine engine) {
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
