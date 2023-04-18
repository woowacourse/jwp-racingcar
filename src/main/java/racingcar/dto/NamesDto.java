package racingcar.dto;

import java.util.List;

public class NamesDto {
    private final List<String> names;

    private NamesDto(List<String> names) {
        this.names = names;
    }

    public static NamesDto of(String input) {
        String[] names = input.split(",");
        return new NamesDto(List.of(names));
    }

    public List<String> getNames() {
        return names;
    }
}
