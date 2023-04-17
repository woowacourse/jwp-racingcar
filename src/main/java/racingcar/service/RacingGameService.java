package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

@Service
public class RacingGameService {
    private final NumberGenerator numberGenerator;
    private final RacingGameMapper racingGameMapper;
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingGameService(
            final NumberGenerator numberGenerator,
            final RacingGameMapper racingGameMapper,
            final GameDao gameDao,
            final CarDao carDao
    ) {
        this.numberGenerator = numberGenerator;
        this.racingGameMapper = racingGameMapper;
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    @Transactional
    public GamePlayResponseDto play(final GamePlayRequestDto gameRequest) {
        final RacingGame game = new RacingGame(numberGenerator, gameRequest.getNames(), gameRequest.getCount());
        game.play();

        final GameEntity gameEntity = racingGameMapper.toGameEntity(gameRequest.getCount());
        final int gameId = gameDao.saveAndGetId(gameEntity)
                .orElseThrow(() -> new IllegalArgumentException("게임 저장에 실패하였습니다."));

        final List<CarEntity> carEntities = racingGameMapper.toCarEntities(game, gameId);
        carDao.saveAll(carEntities);

        return GamePlayResponseDto.of(game.findWinners(), game.getCars());
    }
}
