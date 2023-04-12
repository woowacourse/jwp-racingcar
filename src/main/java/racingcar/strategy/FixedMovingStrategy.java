package racingcar.strategy;

public class FixedMovingStrategy implements MovingStrategy {

    @Override
    public int getRandomNumber() {
        return 5;
    }
}
