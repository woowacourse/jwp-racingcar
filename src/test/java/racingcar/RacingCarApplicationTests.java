package racingcar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarApplicationTests {

    @Autowired
    private RacingCarApplication racingCarApplication;

    @Test
    void contextLoads() {
        assertThat(racingCarApplication).isNotNull();
    }

}
