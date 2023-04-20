package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class OutputViewTest {

    private OutputStream outputStream;

    @BeforeEach
    void setMockOutput() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void setSystemOut() {
        System.setOut(System.out);
    }

    @Test
    void printWinner_메서드_테스트() {
        final List<String> winners = List.of("judy", "nunu");
        OutputView.printWinner(winners);

        assertThat(outputStream.toString()).contains("judy, nunu가 최종 우승했습니다.");
    }

    @Test
    void printStatusGuide_메서드_테스트() {
        OutputView.printStatusGuide();

        assertThat(outputStream.toString()).contains("실행 결과");
    }

    @Test
    void printStatus_메서드_테스트() {
        final String expected = "judy : -" + System.lineSeparator() + "nunu : --" + System.lineSeparator();
        OutputView.printStatus(Map.of("judy", 1, "nunu", 2));

        assertThat(outputStream.toString()).contains(
                "judy : -" + System.lineSeparator(), "nunu : --" + System.lineSeparator());
    }
}
