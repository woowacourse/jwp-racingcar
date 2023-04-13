package racingcar.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

<<<<<<< HEAD:src/main/java/racingcar/controller/dto/RacingRequest.java
public final class RacingRequest {
=======
public final class RacingGameRequestDto {
>>>>>>> b58d181 (refactor: 변경 가능성이 없는 클래스, 파라미터에 final 키워드 부여):src/main/java/racingcar/dto/RacingGameRequestDto.java

    @NotBlank
    private final String names;
    @Positive
    private final int count;

    public RacingRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return this.names;
    }

    public int getCount() {
        return this.count;
    }
}
