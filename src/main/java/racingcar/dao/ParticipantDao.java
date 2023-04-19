package racingcar.dao;

import racingcar.dao.entity.ParticipantEntity;

import java.util.List;

public interface ParticipantDao {
    List<ParticipantEntity> findByGameId(final Long gameId);

    List<ParticipantEntity> findAll();

    void save(final ParticipantEntity participantEntity);
}
