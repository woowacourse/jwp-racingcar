package racing.domain;

public class Name {

    private static final int MIN_CAR_NAME_LENGTH = 1;
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private final String name;

    public Name(final String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
        final String carName = name.strip();
        if (carName.length() < MIN_CAR_NAME_LENGTH || carName.length() > MAX_CAR_NAME_LENGTH) {
            throw new IllegalArgumentException("부적절한 이름입니다.");
        }
    }

    public String getName() {
        return name;
    }
}
