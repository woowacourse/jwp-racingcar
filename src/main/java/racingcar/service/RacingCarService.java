package racingcar.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayersInfoDao;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.dto.*;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacingCarService {

    private static final String DELIMITER = ",";

    private final PlayResultDao playResultDAO;
    private final PlayersInfoDao playersInfoDAO;

    public RacingCarService(PlayResultDao playResultDAO, PlayersInfoDao playersInfoDAO) {
        this.playResultDAO = playResultDAO;
        this.playersInfoDAO = playersInfoDAO;
    }

    public GameResultResponse createResponse(GameInfoRequest gameInfoRequest) {
        List<String> carNames = Arrays.asList(gameInfoRequest.getNames().split(","));
        Cars cars = new Cars(CarFactory.buildCars(carNames));
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        int count = gameInfoRequest.getCount();
        GamePlay.play(cars, count, numberGenerator);
        List<CarParam> carParams = cars.getCars().stream()
                .map(CarParam::new)
                .collect(Collectors.toList());
        List<CarParam> winners = cars.findWinners().stream()
                .map(CarParam::new)
                .collect(Collectors.toList());
        saveResult(count, carParams, winners);
        return new GameResultResponse(cars.findWinners(), cars.getCars());
    }

    private void saveResult(int trialCount, List<CarParam> cars, List<CarParam> winners) {
        int playerResultId = playResultDAO.returnPlayResultIdAfterInsert(trialCount, makeWinnersString(winners));
        playersInfoDAO.insert(playerResultId, cars);
    }

    private String makeWinnersString(List<CarParam> winners) {
        return winners.stream()
                .map(CarParam::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    public List<PlayRecordsResponse> showPlayRecords() {
        return playResultDAO.findAllPlayRecords();
    }
}
