package racingcar.game.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.car.interfaces.Car;
import racingcar.car.interfaces.CarDAO;
import racingcar.car.interfaces.NumberGenerator;
import racingcar.car.model.RacingCar;
import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;
import racingcar.game.interfaces.Game;
import racingcar.game.interfaces.GameDAO;
import racingcar.game.interfaces.GameResult;
import racingcar.game.interfaces.GameService;
import racingcar.game.model.RacingCarGame;

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
    
    private List<Car> generateCars(final String namesLiteral) {
        return Arrays.stream(namesLiteral.split(",")).map(name -> RacingCar.create(name, 0))
                .collect(Collectors.toList());
    }
    
    private GameResult playGame(final int count, final String namesLiteral) {
        final List<Car> cars = this.generateCars(namesLiteral);
        final Game racingCarGame = RacingCarGame.create(this.numberGenerator, cars);
        final Game updatedGame = racingCarGame.race(count);
        return updatedGame.calculateResult();
    }
    
    @Override
    public GameResponseDTO createGame(final GameRequestDTO gameRequestDTO) {
        final int count = gameRequestDTO.getCount();
        final String namesLiteral = gameRequestDTO.getNames();
        final GameResult gameResult = this.playGame(count, namesLiteral);
        final int gameId = this.racingGameDao.insert(count, gameResult);
        this.racingCarDAO.insertAll(gameResult.getCars(), gameId);
        return GameResponseDTO.create(gameResult);
    }
}
