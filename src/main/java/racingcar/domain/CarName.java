package racingcar.domain;

public class CarName {
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 10;

    private final String name;

    public CarName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateName(final String input) {
        String name = removeBlank(input);
        if (name.length() > MAXIMUM_NAME_LENGTH || name.length() < MINIMUM_NAME_LENGTH) {
            String errorMessage = String.format("이름의 길이는 %d이상 %d이하여야 합니다.", MINIMUM_NAME_LENGTH, MAXIMUM_NAME_LENGTH);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private String removeBlank(String input) {
        return input.trim();
    }
}
