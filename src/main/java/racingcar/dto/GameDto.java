package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import racingcar.domain.racinggame.Count;
import racingcar.domain.racinggame.RacingGame;

@EqualsAndHashCode
@ToString
@Getter
public class GameDto {
    private final int count;
    
    public GameDto(final RacingGame racingGame) {
        final Count count = racingGame.getCount();
        this.count = count.getOriginalCount();
    }
}
