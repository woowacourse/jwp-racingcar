package racingcar.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayResponseDto {

    @NotEmpty(message = "플레이 이력 응답 오류: 참여 자동차가 없는 이력이 존재합니다.")
    private final List<CarDto> racingCars;
    @NotBlank(message = "플레이 이력 응답 오류: 우승자가 존재하지 않는 이력이 존재합니다.")
    private final String winners;
}
