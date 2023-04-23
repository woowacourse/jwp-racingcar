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

    // TODO: 4/23/23 [문제1] INSERT로 여러번 DB 접근하는 문제
    // TODO: 4/23/23 [문제1 관련] 도메인에서 조회한 결과를 각각에 디비에 저장할 후, Car <-> PlayerEntity가 매칭이 안됨. 
    public GameResultReponse playGame(final GameStartRequest gameStartRequest) {
        final NumberGenerator numberGenerator = new RandomNumberGenerator();
        final Cars cars = Cars.from(List.of(gameStartRequest.getNames().split(SEPARATOR)));
        final GameRound gameRound = new GameRound(gameStartRequest.getCount());
        final GameManager gameManager = new GameManager(numberGenerator, cars, gameRound);
        gameManager.run();
        List<Car> allCars = gameManager.getAllCars();
        List<Car> winnerCars = gameManager.getWinnerCars();

        Long gameId = gameDao.save(gameStartRequest.getCount()); // TODO: 4/23/23  [문제1 관련] 게임 저장 1번 접근
        List<Long> playerIds = savePlayers(allCars); // TODO: 4/23/23  [문제1 관련] 플레이어 저장 혹은 조회 2 x N번 접근
        participantDao.saveAll(convertParticipantEntities(gameId, playerIds, allCars, winnerCars)); // TODO: 4/23/23 [문제1 관련] 결과 저장 1번 접근
        // TODO: 4/23/23 [문제1 관련] 개선한 점 : 1 + (2 x N) + N 번 접근 -> 1 + (2 x N) + 1 번 접근

        return GameResultReponse.from(allCars, winnerCars);
    }

    private List<ParticipantEntity> convertParticipantEntities(
            final Long gameId,
            final List<Long> playerIds,
            final List<Car> allCars,
            final List<Car> winnerCars) {
        List<ParticipantEntity> participantEntities = new ArrayList<>();
        // TODO: 4/23/23 [문제 1 관련] playerIds와 allCars가 순서가 동일하다고 가정 해야 됨 (여기가 제일 문제 같음)
        for (int index = 0; index < playerIds.size(); index++) {
            Long playerId = playerIds.get(index);
            Car car = allCars.get(index);
            participantEntities.add(new ParticipantEntity(gameId, playerId, car.getPosition(), isWinner(winnerCars, car)));
        }
        return participantEntities;
    }

    // TODO: 4/23/23 [문제1 관련]여기선 플레이어 갯수의 두배(조회 + 저장, 있는지 확인해야 함)만큼  발생
    private List<Long> savePlayers(final List<Car> allCars) {
        List<Long> playerIds = new ArrayList<>();
        for (Car car : allCars) {
            String carName = car.getName();
            Long playerId = findOrSavePlayer(carName);
            playerIds.add(playerId);
        }
        return playerIds;
    }

    private Long findOrSavePlayer(final String carName) {
        Optional<PlayerEntity> player = playerDao.findByName(carName);
        if (player.isEmpty()) {
            return playerDao.save(carName);
        }
        return player.orElseThrow().getId();
    }

    private boolean isWinner(final List<Car> winnerCars, final Car car) {
        String name = car.getName();
        return winnerCars.stream()
                .map(Car::getName)
                .anyMatch(winnerName -> winnerName.equals(name));
    }
}
