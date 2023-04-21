package racingcar.dao;

import java.util.List;
import racingcar.dto.ParticipateDto;
import racingcar.entity.ParticipatesEntity;

public class ConsoleParticipatesDao implements ParticipatesDao {

    @Override
    public void save(final ParticipateDto participateDto) {

    }

    @Override
    public List<ParticipatesEntity> findByGameId(final Long gameId) {
        return null;
    }
}
