package racingcar.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayersInfoDao;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.dto.GameInfoRequest;
import racingcar.dto.GameResultResponse;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class RacingCarService {

    private final PlayResultDao playResultDAO;
    private final PlayersInfoDao playersInfoDAO;

    public RacingCarService(PlayResultDao playResultDAO, PlayersInfoDao playersInfoDAO) {
        this.playResultDAO = playResultDAO;
        this.playersInfoDAO = playersInfoDAO;
    }

    public GameResultResponse createResponse(GameInfoRequest gameInfoRequest){
        List<String> carNames = Arrays.asList(gameInfoRequest.getNames().split(","));
        Cars cars = new Cars(CarFactory.buildCars(carNames));
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        int count = gameInfoRequest.getCount();
        play(cars, count, numberGenerator);
        saveResult(count, cars);
        return new GameResultResponse(cars.findWinners(), cars.getCars());
    }

    private void play(Cars cars, int count, NumberGenerator numberGenerator) {
        while (count-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }
    private void saveResult(int trialCount, Cars cars){
        int playerResultId = playResultDAO.returnPlayResultIdAfterInsert(trialCount, cars.findWinners());
        playersInfoDAO.insert(playerResultId, cars.getCars());
    }
}
