package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.RacingCarController;

@SpringBootTest
class RacingCarApplicationTests {

    @Autowired
    private RacingCarController racingCarController;

    @Test
    void contextLoads() {
        assertThat(racingCarController).isNotNull();
    }

}
