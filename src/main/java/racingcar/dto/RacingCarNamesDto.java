package racingcar.dto;

import java.util.List;

public class RacingCarNamesDto {
    private final List<String> names;

    private RacingCarNamesDto(List<String> names) {
        this.names = names;
    }

    public static RacingCarNamesDto of(String input) {
        String[] names = input.split(",");
        return new RacingCarNamesDto(List.of(names));
    }

    public List<String> getNames() {
        return names;
    }
}
