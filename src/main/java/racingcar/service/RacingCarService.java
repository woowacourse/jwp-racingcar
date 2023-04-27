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
    private final RacingCarPlayRule racingCarPlayRule;

    public RacingCarService(PlayResultDao playResultDao, PlayersInfoDao playersInfoDao, RacingCarPlayRule racingCarPlayRule) {
        this.playResultDao = playResultDao;
        this.playersInfoDao = playersInfoDao;
        this.racingCarPlayRule = racingCarPlayRule;
    }

    public GameResultForResponse createResponse(GameInfoForRequest gameInfoForRequest) {
        List<String> carNames = Arrays.asList(gameInfoForRequest.getNames().split(","));
        Cars cars = CarsFactory.create(carNames);
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        int count = gameInfoForRequest.getCount();
        racingCarPlayRule.moveCarsUntilCountIsOver(cars, count, numberGenerator);
        List<CarForSave> carForSaves = cars.getCars().stream()
                .map(CarForSave::new)
                .collect(Collectors.toList());
        List<CarForSave> winners = cars.findWinners().stream()
                .map(CarForSave::new)
                .collect(Collectors.toList());
        saveResult(count, carForSaves, winners);
        return new GameResultForResponse(cars.findWinners(), cars.getCars());
    }

    private void saveResult(int trialCount, List<CarForSave> carForSaves, List<CarForSave> winners) {
        int playerResultId = playResultDao.returnPlayResultIdAfterInsert(trialCount, makeWinnersString(winners));
        playersInfoDao.insert(playerResultId, carForSaves);
    }

    private String makeWinnersString(List<CarForSave> winners) {
        return winners.stream()
                .map(CarForSave::getName)
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
            List<CarForSave> carForSaves = playersInfos.stream()
                    .map(playersInfo -> new CarForSave(playersInfo.getName(), playersInfo.getPosition()))
                    .collect(Collectors.toList());
            PlayRecordsForResponse playRecordsForResponse = new PlayRecordsForResponse(winners, carForSaves);
            playRecordsForResponses.add(playRecordsForResponse);
        }
        return playRecordsForResponses;
    }
}
