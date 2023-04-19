package racingcar.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class CarDto {

    @NotBlank(message = "플레이 이력 응답 오류: 자동차 이름은 빈 문자열일 수 없습니다.")
    private final String name;

    @PositiveOrZero(message = "플레이 이력 응답 오류: 자동차 이동횟수는 음수일 수 없습니다.")
    private final int position;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarDto that = (CarDto) o;
        return position == that.position && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
