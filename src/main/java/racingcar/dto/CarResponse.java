package racingcar.dto;

public final class CarResponse {

    private final String name;
    private final int position;

    public CarResponse(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }
}
