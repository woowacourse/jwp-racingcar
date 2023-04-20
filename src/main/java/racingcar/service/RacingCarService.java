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
        List<CarResponse> carResponses = cars.getCars().stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
        List<CarResponse> winners = cars.findWinners().stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
        saveResult(count, carResponses, winners);
        return new GameResultResponse(cars.findWinners(), cars.getCars());
    }

    private void saveResult(int trialCount, List<CarResponse> cars, List<CarResponse> winners) {
        int playerResultId = playResultDAO.returnPlayResultIdAfterInsert(trialCount, makeWinnersString(winners));
        playersInfoDAO.insert(playerResultId, cars);
    }

    private String makeWinnersString(List<CarResponse> winners) {
        return winners.stream()
                .map(CarResponse::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    public List<PlayRecordsResponse> showPlayRecords() {
        return playResultDAO.findAllPlayRecords();
    }
}
