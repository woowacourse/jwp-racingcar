package racingcar.dto;

import java.util.Objects;

public class GameInputDto {

    private final String names;
    private final String count;

    public GameInputDto(String names, String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInputDto that = (GameInputDto) o;
        return Objects.equals(names, that.names) && Objects.equals(count, that.count);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(names, count);
    }
}
