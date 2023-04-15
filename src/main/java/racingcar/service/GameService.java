package racingcar.service;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.RecordDto;
import racingcar.repository.RecordDao;
import racingcar.response.PlayResponse;
import racingcar.domain.Cars;
import racingcar.domain.TrialCount;
import racingcar.repository.GameDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GameService {

    private static final String CAR_NAME_DELIMITER = ",";
    private static final int MIN_GAME_ID = 1;

    private final GameDao gameDao;
    private final RecordDao recordDao;

    @Autowired
    public GameService(final GameDao gameDao, final RecordDao recordDao) {
        this.gameDao = gameDao;
        this.recordDao = recordDao;
    }

    @Transactional
    public PlayResponse playRacing(final String names, final int count) {
        TrialCount trialCount = new TrialCount(count);
        Cars cars = createCars(names);

        play(trialCount, cars);

        long savedGameId = gameDao.insert(trialCount.getValue());
        saveResults(savedGameId, cars);

        return new PlayResponse(winnerNames(cars), toCarDto(cars));
    }

    private Cars createCars(final String names) {
        List<String> carNames = Arrays.stream(names.split(CAR_NAME_DELIMITER, -1))
                .map(String::trim)
                .collect(toList());

        return new Cars(carNames);
    }

    private void play(final TrialCount trialCount, final Cars cars) {
        for (int i = 0; i < trialCount.getValue(); i++) {
            cars.moveAll();
        }
    }

    public void saveResults(final long gameId, final Cars cars) {
        List<String> winnerNames = winnerNames(cars);

        for (final Car car : cars.getCars()) {
            recordDao.insert(gameId, winnerNames.contains(car.getName()), car);
        }
    }

    private List<String> winnerNames(final Cars cars) {
        return cars.getWinner().stream()
                .map(Car::getName)
                .collect(toList());
    }

    private List<CarDto> toCarDto(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(car -> new CarDto(car.getName(), car.getDistance()))
                .collect(toList());
    }

    public List<PlayResponse> allGameHistory() {
        List<PlayResponse> playResponses = new ArrayList<>();

        int count = gameDao.countAll();
        for (int gameId = MIN_GAME_ID; gameId <= count; gameId++) {
            List<RecordDto> foundGame = recordDao.findAllByGameId(gameId);

            playResponses.add(createPlayResponse(foundGame));
        }

        return Collections.unmodifiableList(playResponses);
    }

    private PlayResponse createPlayResponse(final List<RecordDto> foundGame) {
        List<String> winners = new ArrayList<>();
        List<CarDto> carDtos = new ArrayList<>();

        for (final RecordDto recordDto : foundGame) {
            decideWinner(winners, recordDto);
            carDtos.add(new CarDto(recordDto.getPlayerName(), recordDto.getPosition()));
        }

        return new PlayResponse(winners, carDtos);
    }

    private void decideWinner(final List<String> winners, final RecordDto recordDto) {
        if (recordDto.isWinner()) {
            winners.add(recordDto.getPlayerName());
        }
    }
}
