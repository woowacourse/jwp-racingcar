package racingcar.entity;

import java.util.Objects;

public class RacingCarEntity {

    private Long id;
    private Long gameId;
    private String name;
    private Integer position;

    private RacingCarEntity(final Long gameId, final String name, final Integer position) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public static RacingCarBuilder builder() {
        return new RacingCarBuilder();
    }

    private static class RacingCarBuilder {

        private Long gameId;
        private String name;
        private Integer position;

        public RacingCarBuilder gameId(final Long gameId) {
            this.gameId = gameId;
            return this;
        }

        public RacingCarBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public RacingCarBuilder position(final Integer position) {
            this.position = position;
            return this;
        }

        public RacingCarEntity build() {
            return new RacingCarEntity(gameId, name, position);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacingCarEntity that = (RacingCarEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
