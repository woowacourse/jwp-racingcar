package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.ParticipantDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.*;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.ParticipateDto;
import racingcar.dto.PlayerDto;
import racingcar.dto.ResultResponse;

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
            ParticipateDto participateDto = new ParticipateDto(gameId, playerId, carPosition, isWinner(winnerCars, car));
            participantDao.save(participateDto);
        }
    }

    private Long findOrSavePlayer(final String carName) {
        Optional<PlayerDto> playerDtoOptional = playerDao.findByName(carName);
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
