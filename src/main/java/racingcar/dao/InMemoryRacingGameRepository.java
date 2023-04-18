package racingcar.dao;

import racingcar.dto.RacingGameDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryRacingGameRepository implements GameRepository {

    private RacingGameDto racingGameDto;

    private InMemoryRacingGameRepository(RacingGameDto racingGameDto) {
        this.racingGameDto = racingGameDto;
    }

    public static InMemoryRacingGameRepository create() {
        RacingGameDto emptyRacingGameDto = new RacingGameDto(Collections.emptyList(), Collections.emptyList(), 0);
        return new InMemoryRacingGameRepository(emptyRacingGameDto);
    }

    @Override
    public void saveGame(RacingGameDto racingGameDto) {
        this.racingGameDto = racingGameDto;
    }

    @Override
    public List<RacingGameDto> selectAllGames() {
        List<RacingGameDto> games = new ArrayList<>();
        games.add(racingGameDto);
        return games;
    }
}
