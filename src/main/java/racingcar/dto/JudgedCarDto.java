package racingcar.dto;

import java.util.Objects;

public class JudgedCarDto {

    private final String name;
    private final int position;
    private final boolean isWinner;

    private JudgedCarDto(final String name, final int position, final boolean isWinner) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public static JudgedCarDto of(final String name, final int position, final boolean isWinner) {
        return new JudgedCarDto(name, position, isWinner);
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JudgedCarDto carDto = (JudgedCarDto) o;
        return position == carDto.position && isWinner == carDto.isWinner && Objects.equals(name, carDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, isWinner);
    }
}
