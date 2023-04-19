package racingcar.repository.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class InMemoryPlayRecordsDao implements PlayRecordsDao {

    private static long ID_START_VALUE = 1L;
    private long id = ID_START_VALUE;
    private final List<PlayRecord> playRecords = new ArrayList<>();

    @Override
    public void insert(final int count) {
        playRecords.add(new PlayRecord(id, count));
        id++;
    }

    @Override
    public long getLastId() {
        return id;
    }

    @Override
    public void clear() {
        playRecords.clear();
    }

    private class PlayRecord {
        private final Long id;
        private final int trialCount;
        private final LocalTime createdAt;

        public PlayRecord(final Long id, final int trialCount) {
            this.id = id;
            this.trialCount = trialCount;
            this.createdAt = LocalTime.now();
        }

        public Long getId() {
            return id;
        }

        public int getTrialCount() {
            return trialCount;
        }

        public LocalTime getCreatedAt() {
            return createdAt;
        }
    }
}
