package racingcar.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.ResultResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RacingCarControllerTest {

    private final RacingCarController racingCarController;

    @Autowired
    public RacingCarControllerTest(final RacingCarController racingCarController) {
        this.racingCarController = racingCarController;
    }

    @DisplayName("정상 작동 후 200 상태 코드 반환한다.")
    @Test
    void play() {
        //given
        NamesAndCountRequest namesAndCountRequest = new NamesAndCountRequest("망고,루카,소니,현구막", 10);
        //when
        ResponseEntity<ResultResponse> responseEntity = racingCarController.play(namesAndCountRequest);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
