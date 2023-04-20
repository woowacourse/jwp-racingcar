package racing.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racing.dao.car.CarDao;
import racing.dao.game.GameDao;
import racing.domain.Game;
import racing.dto.CarDto;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;
import racing.util.NumberGenerator;

@Service
public class WebRacingGameService implements RacingGameService {

    private final CarDao carDao;
    private final GameDao gameDao;

    public WebRacingGameService(final CarDao carDao, final GameDao gameDao) {
        this.carDao = carDao;
        this.gameDao = gameDao;
    }

    @Override
    public GameResultDto playGame(final GameInputDto gameInputDto, final NumberGenerator numberGenerator) {
        final Game game = new Game(gameInputDto.getCount());
        final int gameId = gameDao.insert(game);
        game.joinCars(gameInputDto.getNames());
        game.playGame(numberGenerator);
        game.judgeWinner();
        game.getCars()
            .forEach(car -> carDao.insert(car, gameId));
        return new GameResultDto(game);
    }

    @Override
    public List<GameResultDto> showGames() {
        final List<Integer> allId = gameDao.getAllGameId();
        return allId.stream()
            .map(gameId -> {
                final List<CarDto> carsInGame = carDao.findByGameId(gameId);
                return new GameResultDto(carsInGame);
            })
            .collect(Collectors.toUnmodifiableList());
    }
}
