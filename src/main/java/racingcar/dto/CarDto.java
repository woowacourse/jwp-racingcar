package racingcar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class CarDto {

    @NotBlank(message = "자동차 이름은 빈 문자열일 수 없습니다.")
    private final String name;

    @PositiveOrZero(message = "자동차 이동횟수는 음수일 수 없습니다.")
    private final int position;
}
