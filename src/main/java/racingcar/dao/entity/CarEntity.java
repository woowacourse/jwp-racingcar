package racingcar.dao.entity;

public class CarEntity {
    private final int play_result_id;
    private final String name;
    private final int position;

    public CarEntity(int play_result_id, String name, int position) {
        this.play_result_id = play_result_id;
        this.name = name;
        this.position = position;
    }

    public int getPlay_result_id() {
        return play_result_id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
