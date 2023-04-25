package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingApplicationTests {

    @DisplayName("스프링 부트 프로그램이 잘 실행된다.")
    @Test
    void contextLoads() {
    }

}
