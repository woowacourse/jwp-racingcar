package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameAndPlayerResultEntity;
import racingcar.dao.GameResultDAO;
import racingcar.dao.PlayerResultDAO;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayerResultDto;
import racingcar.dto.response.GameResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private static final String DELIMITER = ",";

    private final GameResultDAO gameResultDAO;
    private final PlayerResultDAO playerResultDAO;

    @Autowired
    public RacingGameService(GameResultDAO gameResultDAO, PlayerResultDAO playerResultDAO) {
        this.gameResultDAO = gameResultDAO;
        this.playerResultDAO = playerResultDAO;
    }

    public GameResponseDto play(List<String> names, int tryCount) {
        RacingGame racingGame = new RacingGame(convertToNames(names));

        racingGame.moveCars(TryCount.from(tryCount));

        String winners = decideWinners(racingGame);
        List<CarDto> carDtos = CarDto.from(racingGame.getCars());

        int savedId = gameResultDAO.save(GameResultDto.from(tryCount));
        playerResultDAO.saveAll(PlayerResultDto.of(carDtos, savedId));

        return new GameResponseDto(winners, carDtos);
    }

    private List<Name> convertToNames(List<String> names) {
        return names.stream()
                .map(Name::from)
                .collect(Collectors.toList());
    }

    private String decideWinners(RacingGame racingGame) {
        return racingGame.decideWinners().getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private String decideWinners(List<CarDto> carDtos) {
        int maxPosition = carDtos.stream()
                .map(CarDto::getPosition)
                .max(Integer::compareTo)
                .orElseThrow();

        return carDtos.stream()
                .filter(carDto -> carDto.getPosition() == maxPosition)
                .map(CarDto::getName)
                .collect(Collectors.joining(","));
    }

    public List<GameResponseDto> findAllGameAndPlayerResults() {
        List<GameAndPlayerResultEntity> allGameAndPlayerEntities = gameResultDAO.findAllWithPlayerResults();

        List<Integer> gameIds = getGameIds(allGameAndPlayerEntities);

        return gameIds.stream()
                .map(gameId -> getGameResponseDto(allGameAndPlayerEntities, gameId))
                .collect(Collectors.toList());
    }

    private List<Integer> getGameIds(List<GameAndPlayerResultEntity> allGameAndPlayerResults) {
        return allGameAndPlayerResults.stream()
                .map(GameAndPlayerResultEntity::getGameId)
                .distinct()
                .collect(Collectors.toList());
    }

    private GameResponseDto getGameResponseDto(List<GameAndPlayerResultEntity> allGameAndPlayerEntities, Integer gameId) {
        List<CarDto> carDtos = allGameAndPlayerEntities.stream()
                .filter(result -> result.getGameId() == gameId)
                .map(result -> CarDto.of(result.getName(), result.getPosition()))
                .collect(Collectors.toList());
        String winners = decideWinners(carDtos);

        return new GameResponseDto(winners, carDtos);
    }
}
