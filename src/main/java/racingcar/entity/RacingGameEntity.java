package racingcar.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class RacingGameEntity {

    private Long id;
    private Integer trialCount;
    private LocalDateTime date;

    private RacingGameEntity(final Integer trialCount, final LocalDateTime date) {
        this.trialCount = trialCount;
        this.date = date;
    }

    public static RacingGameBuilder builder() {
        return new RacingGameBuilder();
    }

    private static class RacingGameBuilder {

        private Integer trialCount;
        private LocalDateTime date;

        public RacingGameBuilder trialCount(final Integer trialCount) {
            this.trialCount = trialCount;
            return this;
        }

        public RacingGameBuilder date(final LocalDateTime date) {
            this.date = date;
            return this;
        }

        public RacingGameEntity build() {
            return new RacingGameEntity(trialCount, date);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacingGameEntity that = (RacingGameEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
