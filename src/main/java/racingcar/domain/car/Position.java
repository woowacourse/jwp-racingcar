package racingcar.domain.car;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Position {
    private final int position;
    
    public Position(int position) {
        this.position = position;
    }
    
    public Position increase() {
        return new Position(position + 1);
    }
}
