package racingcar.dao;

import racingcar.dto.RacingGameDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryRacingRacingGameRepository implements RacingGameRepository {

    private RacingGameDto racingGameDto;

    private InMemoryRacingRacingGameRepository(RacingGameDto racingGameDto) {
        this.racingGameDto = racingGameDto;
    }

    public static InMemoryRacingRacingGameRepository create() {
        RacingGameDto emptyRacingGameDto = new RacingGameDto(Collections.emptyList(), Collections.emptyList(), 0);
        return new InMemoryRacingRacingGameRepository(emptyRacingGameDto);
    }

    @Override
    public void save(RacingGameDto racingGameDto) {
        this.racingGameDto = racingGameDto;
    }

    @Override
    public List<RacingGameDto> selectAll() {
        List<RacingGameDto> games = new ArrayList<>();
        games.add(racingGameDto);
        return games;
    }
}
