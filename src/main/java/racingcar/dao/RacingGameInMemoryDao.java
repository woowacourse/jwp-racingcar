package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import racingcar.dao.dto.RacingGameDto;

public class RacingGameInMemoryDao implements RacingGameDao {

    private final List<RacingGameDto> racingGames = new ArrayList<>();

    private int id = 1;

    @Override
    public int save(String winners, int count) {
        RacingGameDto racingGame = new RacingGameDto(id, winners);
        racingGames.add(racingGame);

        return id++;
    }

    @Override
    public Optional<RacingGameDto> findById(int id) {
        for (final RacingGameDto racingGame : racingGames) {
            if (racingGame.getId() == id) {
                return Optional.of(racingGame);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<RacingGameDto> findAll() {
        return List.copyOf(racingGames);
    }
}
