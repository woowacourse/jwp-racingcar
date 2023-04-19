package racingcar.domain;

public class Name {
    public static final int MAX_LENGTH = 5;

    private final String name;

    public Name(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 5글자 이상 입력할 수 없습니다.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
