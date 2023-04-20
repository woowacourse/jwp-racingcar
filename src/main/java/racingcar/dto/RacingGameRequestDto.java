package racingcar.dto;

import java.util.List;

public class RacingGameRequestDto {

    private static final String DELIMITER = ",";
    private String names;
    private int count;

    public RacingGameRequestDto(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNamesList() {
        return List.of(names.split(DELIMITER));
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }

}
