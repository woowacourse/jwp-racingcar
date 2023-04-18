package racingcar.dto;

import java.util.List;

public class NamesDto {
    public static final String DELIMITER = ",";
    private final List<String> names;

    private NamesDto(List<String> names) {
        this.names = names;
    }

    public static NamesDto of(String input) {
        String[] names = input.split(DELIMITER);
        return new NamesDto(List.of(names));
    }

    public List<String> getNames() {
        return names;
    }
}
