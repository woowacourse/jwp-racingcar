package racingcar.domain;

public class Name {

    private static final int MIN_CAR_NAME_LENGTH = 1;
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private final String name;
    private final int identifier;

    public Name(String name, int order) {
        if (!isProperLength(name) || containsInvalidWord(name)) {
            throw new IllegalArgumentException("부적절한 이름입니다.");
        }
        this.name = name;
        this.identifier = order;
    }

    private boolean isProperLength(final String carName) throws IllegalArgumentException {
        return carName.length() >= MIN_CAR_NAME_LENGTH && carName.length() <= MAX_CAR_NAME_LENGTH;
    }

    private boolean containsInvalidWord(final String carName) {
        return carName.contains("-");
    }

    public String getName() {
        return name;
    }

    public int getIdentifier() {
        return identifier;
    }
}
