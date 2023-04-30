package racingcar.dao.participates;

import java.util.List;
import racingcar.dto.ParticipateDto;
import racingcar.entity.ParticipatesEntity;

public interface ParticipatesDao {

    void save(final ParticipateDto participateDto);

    List<ParticipatesEntity> findByGameId(final Long gameId);
}
