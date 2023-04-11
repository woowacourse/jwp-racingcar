package racingcar.dto;

public class GameInputDto {

    private String names;
    private int count;

    public GameInputDto(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "GameInputDto{" +
                "names='" + names + '\'' +
                ", count=" + count +
                '}';
    }
}
