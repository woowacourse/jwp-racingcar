package racingcar.entity;

import java.util.Objects;

public class RacingCarEntity {

    private Long id;
    private Long gameId;
    private String name;
    private Integer position;

    private RacingCarEntity(final Long id, final Long gameId, final String name, final Integer position) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public static RacingCarBuilder builder() {
        return new RacingCarBuilder();
    }

    public static class RacingCarBuilder {

        private Long id;
        private Long gameId;
        private String name;
        private Integer position;

        public RacingCarBuilder id(final Long id) {
            this.id = id;
            return this;
        }

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
            return new RacingCarEntity(id, gameId, name, position);
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

    public Long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }
}
