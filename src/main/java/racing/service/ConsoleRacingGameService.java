package racing.service;

import java.util.List;
import java.util.stream.Collectors;
import racing.dao.car.CarDao;
import racing.dao.game.GameDao;
import racing.domain.Car;
import racing.domain.Game;
import racing.dto.CarDto;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;
import racing.util.NumberGenerator;
import racing.view.OutputView;

public class ConsoleRacingGameService implements RacingGameService {

    private final CarDao carDao;
    private final GameDao gameDao;

    public ConsoleRacingGameService(final CarDao carDao, final GameDao gameDao) {
        this.carDao = carDao;
        this.gameDao = gameDao;
    }

    @Override
    public GameResultDto playGame(final GameInputDto gameInputDto, final NumberGenerator numberGenerator) {
        final Game game = new Game(gameInputDto.getCount());
        final int gameId = insertGame(game);
        game.playGameWith(gameInputDto.getNames(), numberGenerator);
        insertAllCars(game.getCars(), gameId);
        return new GameResultDto(game);
    }

    private int insertGame(final Game game) {
        return gameDao.insert(game);
    }

    private void insertAllCars(final List<Car> cars, final int gameId) {
        for (Car car : cars) {
            carDao.insert(car, gameId);
        }
    }

    private List<CarDto> convertCarToDto(final List<Car> cars) {
        return cars.stream()
            .map(CarDto::new)
            .collect(Collectors.toUnmodifiableList());
    }

    private void printFinalResultWithCarDtos(final List<CarDto> carDtos) {
        final OutputView outputView = new OutputView();
        outputView.printFinalResult(carDtos);
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
