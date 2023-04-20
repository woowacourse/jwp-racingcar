package racingcar.domain;

public class CarName {

    private static final int MIN_CAR_NAME_LENGTH = 1;
    private static final int MAX_CAR_NAME_LENGTH = 5;

    private final String name;

    public CarName(final String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(final String carName) {
        if (carName.length() < MIN_CAR_NAME_LENGTH || MAX_CAR_NAME_LENGTH < carName.length()) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 자동차 이름 길이는 %d자 이상, %d자 이하여야합니다.", MIN_CAR_NAME_LENGTH, MAX_CAR_NAME_LENGTH));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarName carName = (CarName) o;

        return name.equals(carName.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }
}
