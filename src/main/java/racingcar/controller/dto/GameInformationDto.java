package racingcar.controller.dto;

public class GameInformationDto {

    private final String names;
    private final int count;

    public GameInformationDto(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
