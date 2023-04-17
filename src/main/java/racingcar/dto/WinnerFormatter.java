package racingcar.dto;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import racingcar.domain.Winner;

import java.util.Locale;

@Component
public class WinnerFormatter implements Formatter<Winner> {
    private static final String DELIMETER = ",";

    @Override
    public Winner parse(String text, Locale locale) {
        throw new UnsupportedOperationException("Parsing not supported");
    }

    @Override
    public String print(Winner object, Locale locale) {
        return String.join(DELIMETER, object.getWinnerNames());
    }
}
