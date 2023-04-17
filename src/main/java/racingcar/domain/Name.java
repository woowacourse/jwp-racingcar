package racingcar.domain;

public class Name {
    private static final int MAX_LENGTH = 5;
    private static final int MIN_LENGTH = 1;

    private final String name;

    public Name(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        validateLength(name);
    }

    private void validateLength(final String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름은 5글자 이하로 입력해주세요.");
        }
    }

    public String getName() {
        return this.name;
    }
}

