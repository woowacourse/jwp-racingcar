package racingcar.entity;

public class GameId {
    public static final int FIRST_ID_VALUES = 0;
    private final int id;

    public GameId(final int id) {
        validate(id);
        this.id = id;
    }

    private void validate(int id) {
        if (id < FIRST_ID_VALUES) {
            throw new IllegalArgumentException("잘못된 id가 들어왔습니다.");
        }
    }

    public int getId() {
        return id;
    }
}
