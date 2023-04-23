package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.mapper.PlayerDtoMapper;
import racingcar.dao.mapper.RacingGameDtoMapper;
import racingcar.domain.CarGroup;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;

@Service
@Transactional(readOnly = true)
public class RacingGameService {

    private final RacingGameDao racingGameDao;
    private final PlayerDao playerDao;

    public RacingGameService(final RacingGameDao racingGameDao, final PlayerDao playerDao) {
        this.racingGameDao = racingGameDao;
        this.playerDao = playerDao;
    }

    @Transactional
    public RacingGameResponse race(final CarGroup carGroup, final int count) {
        final RacingGame racingGame = new RacingGame(carGroup, new RandomNumberGenerator());
        raceBy(count, racingGame);
        final String winners = createWinners(racingGame);

        final int racingGameId = racingGameDao.save(winners, count);
        final boolean isSaved = playerDao.save(carGroup, racingGameId);
        if (!isSaved) {
            throw new IllegalStateException("[ERROR] 레이싱 플레이어 저장에 실패하였습니다.");
        }

        final List<CarDto> carDtos = createCarDto(carGroup);

        return new RacingGameResponse(winners, carDtos);
    }

    public List<RacingGameResponse> findHistory() {
        final List<RacingGameDtoMapper> gameHistories = racingGameDao.findAll();
        final List<Integer> gameHistoryIds = getGameHistoryIds(gameHistories);
        final List<List<CarDto>> playerHistories = getPlayerHistories(gameHistoryIds);

        return IntStream.range(0, gameHistories.size())
                .mapToObj(i -> new RacingGameResponse(gameHistories.get(i).getWinners(), playerHistories.get(i)))
                .collect(Collectors.toList());
    }

    private void raceBy(final int count, final RacingGame racingGame) {
        for (int i = 0; i < count; i++) {
            racingGame.race();
        }
    }

    private String createWinners(final RacingGame racingGame) {
        return racingGame.produceRacingResult()
                .pickWinner()
                .stream()
                .map(Name::getName)
                .collect(Collectors.joining(","));
    }

    private List<CarDto> createCarDto(final CarGroup carGroup) {
        return carGroup.getCars().stream()
                .map(car -> new CarDto(car.getName().getName(), car.getPosition().getPosition()))
                .collect(Collectors.toList());
    }

    private List<Integer> getGameHistoryIds(final List<RacingGameDtoMapper> gameHistories) {
        final List<Integer> gameHistoryIds = new ArrayList<>();
        for (final RacingGameDtoMapper gameHistory : gameHistories) {
            gameHistoryIds.add(gameHistory.getId());
        }
        return gameHistoryIds;
    }

    private List<List<CarDto>> getPlayerHistories(final List<Integer> gameHistoryIds) {
        final List<List<CarDto>> playerHistories = new ArrayList<>();
        for (final Integer gameHistoryId : gameHistoryIds) {
            final List<PlayerDtoMapper> playerDtoMappers = playerDao.findAllByRacingGameId(gameHistoryId);
            final List<CarDto> carDtos = createCarDtos(playerDtoMappers);
            playerHistories.add(carDtos);
        }
        return playerHistories;
    }

    private List<CarDto> createCarDtos(final List<PlayerDtoMapper> playerDtoMappers) {
        final List<CarDto> carDtos = new ArrayList<>();
        for (final PlayerDtoMapper playerDtoMapper : playerDtoMappers) {
            final CarDto carDto = new CarDto(playerDtoMapper.getName(), playerDtoMapper.getPosition());
            carDtos.add(carDto);
        }
        return carDtos;
    }
}
