package racingcar.entity;

import java.util.Objects;

public class RacingWinnerEntity {

    private Long id;
    private Long gameId;
    private Long carId;

    private RacingWinnerEntity(final Long id, final Long gameId, final Long carId) {
        this.id = id;
        this.gameId = gameId;
        this.carId = carId;
    }

    public static RacingWinnerBuilder builder() {
        return new RacingWinnerBuilder();
    }

    public static class RacingWinnerBuilder {

        private Long id;
        private Long gameId;
        private Long carId;

        public RacingWinnerBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public RacingWinnerBuilder gameId(final Long gameId) {
            this.gameId = gameId;
            return this;
        }

        public RacingWinnerBuilder carId(final Long carId) {
            this.carId = carId;
            return this;
        }

        public RacingWinnerEntity build() {
            return new RacingWinnerEntity(id, gameId, carId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacingWinnerEntity that = (RacingWinnerEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getCarId() {
        return carId;
    }
}
