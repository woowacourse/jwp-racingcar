package racingcar.dto;


public class PlayRequestDto {

    private String names;
    private int count;

    public PlayRequestDto() {
    }

    public PlayRequestDto(String names, int count) {
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
        return "PlayRequestDto{" +
                "names='" + names + '\'' +
                ", count=" + count +
                '}';
    }
}
