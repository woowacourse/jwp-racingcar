package racingcar.dto;

public class PlayRequestDto {

    private String names;
    private int count;

    public PlayRequestDto() {
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "PlayRequestDto{" +
                "names='" + names + '\'' +
                ", count=" + count +
                '}';
    }
}
