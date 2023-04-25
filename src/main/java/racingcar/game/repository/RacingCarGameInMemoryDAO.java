package racingcar.game.repository;

import java.util.ArrayList;
import java.util.List;
import racingcar.game.model.GameResult;

public class RacingCarGameInMemoryDAO implements GameDAO {
    
    public static final String NU_SUCH_GAME_ID_ERROR = "해당 게임 아이디가 존재하지 않습니다.";
    private final List<GameResult> gameRepository = new ArrayList<>();
    
    @Override
    public int insert(final int count, final GameResult gameResult) {
        this.gameRepository.add(gameResult);
        return this.gameRepository.size();
    }
    
    @Override
    public GameResult find(final int gameId) {
        if (this.gameRepository.size() < gameId) {
            throw new IllegalArgumentException(NU_SUCH_GAME_ID_ERROR);
        }
        return this.gameRepository.get(gameId);
    }
    
    @Override
    public List<GameResult> findAll() {
        return List.copyOf(this.gameRepository);
    }
}
