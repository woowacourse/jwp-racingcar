package racingcar.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RecordDto {

    private final int gameId;
    private final String playerName;
    private final int position;
    private final boolean isWinner;
}
