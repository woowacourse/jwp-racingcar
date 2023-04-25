package racingcar.game.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.car.model.NumberGenerator;
import racingcar.car.repository.CarDAO;
import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;
import racingcar.game.model.Game;
import racingcar.game.model.GameResult;
import racingcar.game.repository.GameDAO;

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
