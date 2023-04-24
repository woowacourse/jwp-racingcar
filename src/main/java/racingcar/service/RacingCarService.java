package racingcar.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayersInfoDao;
import racingcar.domain.CarsFactory;
import racingcar.domain.Cars;
import racingcar.dto.*;
import racingcar.entity.PlayResult;
import racingcar.entity.PlayersInfo;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacingCarService {

    private static final String DELIMITER = ",";

    private final PlayResultDao playResultDao;
    private final PlayersInfoDao playersInfoDao;
    private final GamePlay gamePlay;

    public RacingCarService(PlayResultDao playResultDao, PlayersInfoDao playersInfoDao, GamePlay gamePlay) {
        this.playResultDao = playResultDao;
        this.playersInfoDao = playersInfoDao;
        this.gamePlay = gamePlay;
    }

    public GameResultForResponse createResponse(GameInfoForRequest gameInfoForRequest) {
        List<String> carNames = Arrays.asList(gameInfoForRequest.getNames().split(","));
        Cars cars = CarsFactory.buildCarsFromFactory(carNames);
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        int count = gameInfoForRequest.getCount();
        gamePlay.play(cars, count, numberGenerator);
        List<CarForNameAndPosition> carResponse = cars.getCars().stream()
                .map(CarForNameAndPosition::new)
                .collect(Collectors.toList());
        List<CarForNameAndPosition> winners = cars.findWinners().stream()
                .map(CarForNameAndPosition::new)
                .collect(Collectors.toList());
        saveResult(count, carResponse, winners);
        return new GameResultForResponse(cars.findWinners(), cars.getCars());
    }

    private void saveResult(int trialCount, List<CarForNameAndPosition> carForNameAndPositions, List<CarForNameAndPosition> winners) {
        int playerResultId = playResultDao.returnPlayResultIdAfterInsert(trialCount, makeWinnersString(winners));
        playersInfoDao.insert(playerResultId, carForNameAndPositions);
    }

    private String makeWinnersString(List<CarForNameAndPosition> winners) {
        return winners.stream()
                .map(CarForNameAndPosition::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    public List<PlayRecordsForResponse> showPlayRecords() {
        List<String> allWinners = playResultDao.findAllPlayResults().stream()
                .map(PlayResult::getWinners).collect(Collectors.toList());
        List<PlayRecordsForResponse> playRecordsForResponses = new ArrayList<>();
        for (int i = 0; i < allWinners.size(); i++) {
            String winners = allWinners.get(i);
            int playResultId = i + 1;
            List<PlayersInfo> playersInfos = playersInfoDao.findPlayersInfosByPlayResultId(playResultId);
            List<CarForNameAndPosition> carForNameAndPositions = playersInfos.stream()
                    .map(playersInfo -> new CarForNameAndPosition(playersInfo.getName(), playersInfo.getPosition()))
                    .collect(Collectors.toList());
            PlayRecordsForResponse playRecordsForResponse = new PlayRecordsForResponse(winners, carForNameAndPositions);
            playRecordsForResponses.add(playRecordsForResponse);
        }
        return playRecordsForResponses;
    }
}
