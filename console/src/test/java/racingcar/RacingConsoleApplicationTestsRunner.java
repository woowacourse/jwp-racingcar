package racingcar;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RacingConsoleApplicationTestsRunner {

    @BeforeAll
    public static void setUp() {
        final String input = "a,b,c,d" + System.lineSeparator() + "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @AfterAll
    public static void reset() {
        System.setIn(System.in);
    }

    @Test
    void contextLoads() {
    }
}
