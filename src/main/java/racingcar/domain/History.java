package racingcar.domain;

public class History {
    private final Name name;
    private final Position position;

    public History(final Name name, final Position position) {
        this.name = name;
        this.position = position;
    }

    public Name getName() {
        return name;
    }

    public int getPositionValue() {
        return this.position.getValue();
    }
}
