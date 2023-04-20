package racing.dto;

import java.beans.ConstructorProperties;

public class GameInputDto {

    private final String names;
    private final String count;

    @ConstructorProperties(value = {"names", "count"})
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
}
