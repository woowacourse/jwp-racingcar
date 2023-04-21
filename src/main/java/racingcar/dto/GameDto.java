package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import racingcar.domain.racinggame.RacingGame;
import racingcar.domain.racinggame.Count;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
@ToString
@Getter
public class GameDto {
    private final int count;
    private final String date;
    private final String time;
    
    public GameDto(final RacingGame racingGame) {
        final Count count = racingGame.getCount();
        this.count = count.getOriginalCount();
        
        final ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        final LocalDate localDate = LocalDate.now(koreaZoneId);
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 원하는 날짜 형식을 지정합니다.
        this.date = localDate.format(dateFormatter);
        
        final LocalTime localTime = LocalTime.now(koreaZoneId);
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.time = localTime.format(timeFormatter);
    }
}
