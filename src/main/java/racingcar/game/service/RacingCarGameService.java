package racingcar.game.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.car.interfaces.CarDAO;
import racingcar.car.interfaces.NumberGenerator;
import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;
import racingcar.game.interfaces.Game;
import racingcar.game.interfaces.GameDAO;
import racingcar.game.interfaces.GameResult;
import racingcar.game.interfaces.GameService;

@Repository
public class RacingCarGameService implements GameService {
    
    private final NumberGenerator numberGenerator;
    private final GameDAO racingGameDao;
    private final CarDAO racingCarDAO;
    
    public RacingCarGameService(final NumberGenerator numberGenerator, final GameDAO racingGameDao,
            final CarDAO racingCarDAO) {
        this.numberGenerator = numberGenerator;
        this.racingGameDao = racingGameDao;
        this.racingCarDAO = racingCarDAO;
    }
    
    @Override
    public GameResponseDTO createGame(final GameRequestDTO gameRequestDTO) {
        final int count = gameRequestDTO.getCount();
        final String namesLiteral = gameRequestDTO.getNames();
        final GameResult gameResult = Game.run(this.numberGenerator, count, namesLiteral);
        final int gameId = this.racingGameDao.insert(count, gameResult);
        this.racingCarDAO.insertAll(gameResult.getCars(), gameId);
        return GameResponseDTO.create(gameResult);
    }
    
    @Override
    public List<GameResponseDTO> retrieveAllGames() {
        final List<GameResult> gameResults = this.racingGameDao.findAll();
        return gameResults.stream().map(GameResponseDTO::create).collect(Collectors.toList());
    }
}
