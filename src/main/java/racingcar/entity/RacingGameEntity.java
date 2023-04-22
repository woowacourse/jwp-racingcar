package racingcar.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class RacingGameEntity {

    private Long id;
    private Integer trialCount;
    private LocalDateTime date;

    private RacingGameEntity(final Long id, final Integer trialCount, final LocalDateTime date) {
        this.id = id;
        this.trialCount = trialCount;
        this.date = date;
    }

    public static RacingGameBuilder builder() {
        return new RacingGameBuilder();
    }

    public static class RacingGameBuilder {

        private Long id;
        private Integer trialCount;
        private LocalDateTime date;

        public RacingGameBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public RacingGameBuilder trialCount(final Integer trialCount) {
            this.trialCount = trialCount;
            return this;
        }

        public RacingGameBuilder date(final LocalDateTime date) {
            this.date = date;
            return this;
        }

        public RacingGameEntity build() {
            return new RacingGameEntity(id, trialCount, date);
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

    public Long getId() {
        return id;
    }

    public Integer getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
