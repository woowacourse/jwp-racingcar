package racingcar.exception;

public class IllegalGameStateException extends GameException {

    public IllegalGameStateException(final String message) {
        super(message);
    }
}
