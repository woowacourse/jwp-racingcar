package racingcar.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Name {
    public static final int MAX_NAME_LENGTH = 5;
    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "입력값은 비어있을 수 없습니다.";
    public static final String INVALID_NAME_LENGTH_EXCEPTION_MESSAGE = "5글자 까지만 가능합니다.";

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        validateName(name);
        return new Name(name);
    }

    public static List<Name> of(List<String> names) {
        ArrayList<Name> carNames = new ArrayList<>();
        names.forEach(name -> carNames.add(Name.of(name)));
        return carNames;
    }

    private static void validateName(String name) {
        validateBlank(name);

        validateLength(name);
    }

    private static void validateLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    private static void validateBlank(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
