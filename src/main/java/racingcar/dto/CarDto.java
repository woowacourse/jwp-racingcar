package racingcar.dto;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/20
 */
public class CarDto {

    private final String name;
    private final int position;

    private CarDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static CarDto of(final String name, final int position) {
        return new CarDto(name, position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
