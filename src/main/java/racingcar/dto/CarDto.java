package racingcar.dto;

public final class CarDto {

    private final String name;
    private final int position;
    private boolean isWinner;
    private Long id;

    public CarDto(final Long id, final String name, final int position, final boolean isWinner) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
        this.id = id;
    }

    public CarDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public Long getId() {
        return id;
    }
}
