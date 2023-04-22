package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.entity.GameEntity;
import racingcar.entity.PlayerEntity;

@Service
public class RacingCarService {

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final NumberGenerator numberGenerator;

    public RacingCarService(GameDao gameDao, PlayerDao playerDao, NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public PlayResponse play(PlayRequest request) {
        Race race = new Race(request.getCount(), request.getNames(), numberGenerator);
        race.play();

        List<Car> winners = race.findWinners();

        GameEntity gameEntity = GameEntity.from(request.getCount());
        Long gameId = gameDao.insert(gameEntity);

        List<PlayerEntity> playerEntities = race.getCars().stream()
                .map(car -> PlayerEntity.of(car, gameId.intValue(), winners.contains(car)))
                .collect(Collectors.toList());
        playerDao.insert(playerEntities);

        return PlayResponse.from(playerEntities);
    }

    @Transactional(readOnly = true)
    public List<PlayResponse> findHistory() {
        List<GameEntity> gameEntities = gameDao.findAll();
        List<PlayerEntity> playerEntities = playerDao.findAll();

        List<PlayResponse> responses = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            List<PlayerEntity> gamePlayerEntities = playerEntities.stream()
                    .filter(player -> player.getGameId() == gameEntity.getId())
                    .collect(Collectors.toList());
            responses.add(PlayResponse.from(gamePlayerEntities));
        }

        return responses;
    }
}
