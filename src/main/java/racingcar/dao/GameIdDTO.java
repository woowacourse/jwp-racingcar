package racingcar.dao;

import java.util.Objects;

public class GameIdDTO {
    private final Long id;

    public GameIdDTO(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final GameIdDTO gameIdDTO = (GameIdDTO) o;
        return Objects.equals(id, gameIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GameIdDTO{" +
                "number=" + id +
                '}';
    }
}
