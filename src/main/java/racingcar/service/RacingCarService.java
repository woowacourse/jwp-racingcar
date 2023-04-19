package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.ParticipantDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.*;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.ResultResponse;
import racingcar.entity.GameEntity;
import racingcar.entity.ParticipantEntity;
import racingcar.entity.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RacingCarService {

    public static final String SEPARATOR = ",";

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final ParticipantDao participantDao;

    @Autowired
    public RacingCarService(final GameDao gameDao, final PlayerDao playerDao, final ParticipantDao participantDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.participantDao = participantDao;
    }

    public List<ResultResponse> searchAllGame() {
        List<GameEntity> allGame = gameDao.findAll();
        List<ResultResponse> allHistories = new ArrayList<>();
        for (GameEntity gameEntity : allGame) {
            Long gameId = gameEntity.getId();
            List<ParticipantEntity> participants = participantDao.findByGameId(gameId);
            List<String> winners = new ArrayList<>();
            List<RacingCarResponse> racingCarResponses = new ArrayList<>();
            for (ParticipantEntity participant : participants) {
                Long playerId = participant.getPlayerId();
                PlayerEntity player = playerDao.findById(playerId).orElseThrow();
                RacingCarResponse racingCarResponse = new RacingCarResponse(
                        player.getName(),
                        participant.getPosition()
                );
                racingCarResponses.add(racingCarResponse);
                if (participant.getWinner()) {
                    winners.add(player.getName());
                }
            }
            ResultResponse resultResponse = new ResultResponse(
                    String.join(",", winners),
                    racingCarResponses
            );
            allHistories.add(resultResponse);
        }
        return allHistories;
    }

    public ResultResponse playGame(final NamesAndCountRequest namesAndCount) {
        final NumberGenerator numberGenerator = new RandomNumberGenerator();
        final Cars cars = Cars.from(List.of(namesAndCount.getNames().split(SEPARATOR)));
        final GameRound gameRound = new GameRound(namesAndCount.getCount());

        final GameManager gameManager = new GameManager(numberGenerator, cars, gameRound);
        while (!gameManager.isEnd()) {
            gameManager.playGameRound();
        }

        List<Car> allCars = gameManager.getAllCars();
        List<Car> winnerCars = gameManager.getWinnerCars();

        saveGameAndPlayerAndParticipates(namesAndCount.getCount(), allCars, winnerCars);
        return ResultResponse.from(allCars, winnerCars);
    }

    private void saveGameAndPlayerAndParticipates(final int trialCount, final List<Car> allCars, final List<Car> winnerCars) {
        Long gameId = gameDao.save(trialCount);
        for (Car car : allCars) {
            String carName = car.getName();
            int carPosition = car.getPosition();
            Long playerId = findOrSavePlayer(carName);
            ParticipantEntity participantEntity = new ParticipantEntity(gameId, playerId, carPosition, isWinner(winnerCars, car));
            participantDao.save(participantEntity);
        }
    }

    private Long findOrSavePlayer(final String carName) {
        Optional<PlayerEntity> playerDtoOptional = playerDao.findByName(carName);
        if (playerDtoOptional.isEmpty()) {
            return playerDao.save(carName);
        }
        return playerDtoOptional.orElseThrow().getId();
    }

    private boolean isWinner(final List<Car> winnerCars, final Car car) {
        for (Car winnerCar : winnerCars) {
            if (winnerCar.getName().equals(car.getName())) {
                return true;
            }
        }
        return false;
    }
}
