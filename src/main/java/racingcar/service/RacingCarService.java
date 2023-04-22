package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameStartRequest;
import racingcar.controller.dto.CarStateResponse;
import racingcar.controller.dto.GameResultReponse;
import racingcar.dao.GameDao;
import racingcar.dao.ParticipantDao;
import racingcar.dao.PlayerDao;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.ParticipantEntity;
import racingcar.dao.entity.PlayerEntity;
import racingcar.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<GameResultReponse> searchAllGame() {
        List<GameEntity> allGames = gameDao.findAll();
        List<PlayerEntity> allPlayers = playerDao.findAll();
        List<ParticipantEntity> allParticipants = participantDao.findAll();

        return joinGameAndParticipantAndPlayer(allGames, allPlayers, allParticipants);
    }

    private List<GameResultReponse> joinGameAndParticipantAndPlayer(
            final List<GameEntity> allGames,
            final List<PlayerEntity> allPlayers,
            final List<ParticipantEntity> allParticipants
    ) {
        List<GameResultReponse> gameResultResponses = new ArrayList<>();
        for (GameEntity game : allGames) {
            List<ParticipantEntity> participantsPerGame = joinGameAndParticipant(allParticipants, game);
            GameResultReponse gameResultReponse = joinPlayerAndParticipant(allPlayers, participantsPerGame);
            gameResultResponses.add(gameResultReponse);
        }
        return gameResultResponses;
    }

    private List<ParticipantEntity> joinGameAndParticipant(
            final List<ParticipantEntity> allParticipants,
            final GameEntity game
    ) {
        return allParticipants.stream()
                .filter(participant -> participant.getGameId().equals(game.getId()))
                .collect(Collectors.toList());
    }

    private GameResultReponse joinPlayerAndParticipant(
            final List<PlayerEntity> allPlayers,
            final List<ParticipantEntity> participantsPerGame
    ) {
        String winners = getWinner(allPlayers, participantsPerGame);
        List<CarStateResponse> carStateResponses = getRacingCarResponses(allPlayers, participantsPerGame);
        return new GameResultReponse(winners, carStateResponses);
    }

    private List<CarStateResponse> getRacingCarResponses(
            final List<PlayerEntity> allPlayers,
            final List<ParticipantEntity> participantsPerGame
    ) {
        return participantsPerGame.stream()
                .map(participant -> new CarStateResponse(
                        getName(allPlayers, participant.getPlayerId()),
                        participant.getPosition()))
                .collect(Collectors.toList());
    }

    private String getWinner(final List<PlayerEntity> allPlayers, final List<ParticipantEntity> participantsPerGame) {
        return participantsPerGame.stream()
                .filter(ParticipantEntity::getWinner)
                .map(participant -> getName(allPlayers, participant.getPlayerId()))
                .collect(Collectors.joining(SEPARATOR));
    }

    private String getName(final List<PlayerEntity> allPlayers, final Long playerId) {
        return allPlayers.stream()
                .filter(player -> player.getId().equals(playerId))
                .findFirst()
                .orElseThrow()
                .getName();
    }

    public GameResultReponse playGame(final GameStartRequest namesAndCount) {
        final NumberGenerator numberGenerator = new RandomNumberGenerator();
        final Cars cars = Cars.from(List.of(namesAndCount.getNames().split(SEPARATOR)));
        final GameRound gameRound = new GameRound(namesAndCount.getCount());
        final GameManager gameManager = new GameManager(numberGenerator, cars, gameRound);
        gameManager.run();

        List<Car> allCars = gameManager.getAllCars();
        List<Car> winnerCars = gameManager.getWinnerCars();
        saveGameAndPlayerAndParticipates(namesAndCount.getCount(), allCars, winnerCars);
        return GameResultReponse.from(allCars, winnerCars);
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
