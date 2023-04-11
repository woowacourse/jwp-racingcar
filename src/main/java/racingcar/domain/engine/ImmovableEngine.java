package racingcar.domain.engine;

public class ImmovableEngine implements Engine {
    @Override
    public boolean isMovable() {
        return false;
    }
}
