package racingcar.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.dto.PlayResultDto;

public class InMemoryPlayResultDao implements PlayResultDao {
    private final List<PlayResultDto> playResultDtos = new ArrayList<>();

    @Override
    public Long insertWithKeyHolder(int trialCount, List<String> winners) {
        PlayResultDto playResultDto = new PlayResultDto(1, String.join(",", winners));
        playResultDtos.add(playResultDto);
        return 1L;
    }

    @Override
    public List<PlayResultDto> selectAll() {
        return Collections.unmodifiableList(playResultDtos);
    }
}
