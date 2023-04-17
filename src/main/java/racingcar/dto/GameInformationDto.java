package racingcar.dto;

import java.util.List;

public class GameInformationDto {

    private final List<String> names;
    private final String count;

    public GameInformationDto(List<String> names, String count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
