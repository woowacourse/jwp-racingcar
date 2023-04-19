package racingcar.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.mock.SystemInMock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InputViewTest {
    private InputStream inputStream;
    
    @ParameterizedTest(name = "{displayName} : names = {0}")
    @ValueSource(strings = {"abel,스플릿", "asd,asdf"})
    void 자동차_이름_입력_시_영어와_한글과_쉼표만_포함된_경우_입력_값을_그대로_반환한다(String names) {
        // given
        inputStream = new ByteArrayInputStream(names.getBytes());
        System.setIn(inputStream);
        
        // when
        final String inputCarNames = new InputView().inputCarNames();
        
        // then
        assertThat(inputCarNames).isEqualTo(names);
    }
    
    @Test
    void 자동차_이름_입력_시_null일_때_예외가_발생한다() {
        // given
        inputStream = new SystemInMock(true);
        System.setIn(inputStream);
        
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new InputView().inputCarNames())
                .withMessage("null 또는 empty가 올 수 없습니다. 다시 입력해주세요. 입력된 names : null");
    }
    
    @Test
    void 자동차_이름_입력_시_empty일_때_예외가_발생한다() {
        // given
        inputStream = new ByteArrayInputStream("\n".getBytes());
        System.setIn(inputStream);
        
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new InputView().inputCarNames())
                .withMessage("null 또는 empty가 올 수 없습니다. 다시 입력해주세요. 입력된 names : ");
    }
    
    @ParameterizedTest(name = "{displayName} : names = {0}")
    @ValueSource(strings = {"abel, 스플릿", "ab@", "abel.스플릿"})
    void 자동차_이름_입력_시_영어와_한글과_쉼표_외의_문자가_포함된_경우_예외가_발생한다(String names) {
        // given
        inputStream = new ByteArrayInputStream(names.getBytes());
        System.setIn(inputStream);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new InputView().inputCarNames())
                .withMessage("차 이름은 쉼표로 구분해서 영어와 한글로만 입력할 수 있습니다. 다시 입력해주세요. 입력된 names : " + names);
    }
    
    @ParameterizedTest(name = "{displayName} : names = {0}")
    @ValueSource(strings = {"0", "1", "999999", "1000000"})
    void 시도_횟수_입력_시_숫자만_포함된_경우_입력_값을_그대로_반환한다(String tryNumber) {
        // given
        inputStream = new ByteArrayInputStream(tryNumber.getBytes());
        System.setIn(inputStream);
        
        // when
        final int inputTryNumber = new InputView().inputTryNumber();
        
        // then
        assertThat(inputTryNumber).isEqualTo(Integer.parseInt(tryNumber));
    }
    
    @Test
    void 시도_횟수_입력_시_null일_때_예외가_발생한다() {
        // given
        inputStream = new SystemInMock(true);
        System.setIn(inputStream);
        
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new InputView().inputTryNumber())
                .withMessage("null 또는 empty가 올 수 없습니다. 다시 입력해주세요. 입력된 names : null");
    }
    
    @Test
    void 시도_횟수_입력_시_empty일_때_예외가_발생한다() {
        // given
        inputStream = new ByteArrayInputStream("\n".getBytes());
        System.setIn(inputStream);
        
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new InputView().inputTryNumber())
                .withMessage("null 또는 empty가 올 수 없습니다. 다시 입력해주세요. 입력된 names : ");
    }
    
    @AfterEach
    void tearDown() throws IOException {
        inputStream.close();
    }
}
