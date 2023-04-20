package racingcar.web.controller.dto;

public class GameInformationDto {

    private String names;
    private int count;

    public GameInformationDto() {
    }

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
