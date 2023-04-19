package racingcar.domain;

public class Name {
    private static final int MIN_LENGTH_OF_NAME = 1;
    private static final int MAX_LENGTH_OF_NAME = 5;
    private final String name;
    
    public Name(String name) {
        isValid(name);
        this.name = name;
    }
    
    private void isValid(String name) {
        validateOutOfRange(name);
    }
    
    private void validateOutOfRange(String name) {
        if (name.length() < MIN_LENGTH_OF_NAME || name.length() > MAX_LENGTH_OF_NAME) {
            throw new IllegalArgumentException("이름의 길이를 벗어났습니다. 다시 입력해주세요.");
        }
    }
}
