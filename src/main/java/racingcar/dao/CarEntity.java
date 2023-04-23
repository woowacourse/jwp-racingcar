package racingcar.dao;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/20
 */
public class CarEntity {

    private final int id;
    private final String name;
    private final int position;

    public CarEntity(final int id, final String name, final int position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
