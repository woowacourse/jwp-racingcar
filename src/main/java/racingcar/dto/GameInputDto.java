package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class GameInputDto {
    private String names;
    private String count;
    
    public GameInputDto(final String names, final String count) {
        this.names = names;
        this.count = count;
    }
}
