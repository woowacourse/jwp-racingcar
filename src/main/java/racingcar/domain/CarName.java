package racingcar.domain;

public class CarName {

    public static final int MIN_CAR_NAME_LENGTH = 1;
    public static final int MAX_CAR_NAME_LENGTH = 5;
    public static final String CAR_NAME_LENGTH_ERROR_MESSAGE =
            "자동차 이름 길이는 " + MIN_CAR_NAME_LENGTH + "d자 이상, " + MAX_CAR_NAME_LENGTH + "자 이하여야합니다.";
    
    private final String name;

    public CarName(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String carName) {
        if (carName.length() < MIN_CAR_NAME_LENGTH || MAX_CAR_NAME_LENGTH < carName.length()) {
            throw new IllegalArgumentException(CAR_NAME_LENGTH_ERROR_MESSAGE);
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
