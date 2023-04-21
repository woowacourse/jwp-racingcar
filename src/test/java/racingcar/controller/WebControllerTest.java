package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.ResultResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WebControllerTest {

    private final WebController webController;

    @Autowired
    public WebControllerTest(final WebController webController) {
        this.webController = webController;
    }

    @DisplayName("정상 작동 후 201 상태 코드 반환한다.")
    @Test
    void play() {
        //given
        NamesAndCountRequest namesAndCountRequest = new NamesAndCountRequest("망고,루카,소니,현구막", 10);
        //when
        ResponseEntity<ResultResponse> responseEntity = webController.postPlays(namesAndCountRequest);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
