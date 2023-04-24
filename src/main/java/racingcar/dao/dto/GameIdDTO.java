package racingcar.dao.dto;

public class GameIdDTO {
    private final Long id;

    public GameIdDTO(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GameIdDTO{" +
                "number=" + id +
                '}';
    }
}
