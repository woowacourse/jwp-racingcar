package racingcar.domain.strategy.move;

@FunctionalInterface
public interface MoveStrategy {
    boolean isMovable();
}
