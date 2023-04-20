package racingcar.domain.car;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class Position {
    private final int position;
    
    public Position(final int position) {
        this.position = position;
    }
    
    public Position increase() {
        return new Position(position + 1);
    }
    
    public int compareTo(final Position otherPosition) {
        return this.position - otherPosition.position;
    }
}
