package racingcar.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor
public class GameRequestDto {
    @Pattern(regexp = "([a-zA-Z가-힣]+(,[a-zA-Z가-힣]+)*)|", message = "[ERROR] 차 이름은 쉼표로 구분해서 영어와 한글로만 입력할 수 있습니다. 다시 입력해주세요.")
    @NotBlank(message = "[ERROR] 이름을 입력해주세요.")
    private String names;
    @NotBlank(message = "[ERROR] 시도 횟수를 입력해주세요.")
    private String count;
    
    @Builder
    public GameRequestDto(final String names, final String count) {
        this.names = names;
        this.count = count;
    }
}
