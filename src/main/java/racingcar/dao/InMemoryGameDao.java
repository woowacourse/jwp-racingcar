package racingcar.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.dto.PlayResultDto;

public class InMemoryGameDao implements GameDao {
    private final List<PlayResultDto> playResultDtos = new ArrayList<>();

    @Override
    public Long insert(int trialCount, List<String> winners) {
        PlayResultDto playResultDto = new PlayResultDto(1, String.join(",", winners));
        playResultDtos.add(playResultDto);
        return 1L;
    }

    @Override
    public List<PlayResultDto> selectAll() {
        return Collections.unmodifiableList(playResultDtos);
    }
}
