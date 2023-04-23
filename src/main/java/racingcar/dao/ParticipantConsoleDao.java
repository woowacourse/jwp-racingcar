package racingcar.dao;

import racingcar.dao.entity.ParticipantEntity;

import java.util.Collections;
import java.util.List;

public class ParticipantConsoleDao implements ParticipantDao {
    @Override
    public List<ParticipantEntity> findByGameId(final Long gameId) {
        return Collections.emptyList();
    }

    @Override
    public List<ParticipantEntity> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void save(final ParticipantEntity participantEntity) {
    }

    @Override
    public void saveAll(final List<ParticipantEntity> participantEntities) {
    }
}
