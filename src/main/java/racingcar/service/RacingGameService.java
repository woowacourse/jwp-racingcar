package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.JdbcTemplateDAO;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameResultResponseDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private final JdbcTemplateDAO jdbcTemplateDAO;

    public RacingGameService(JdbcTemplateDAO jdbcTemplateDAO) {
        this.jdbcTemplateDAO = jdbcTemplateDAO;
    }

    public GameResultResponseDto play(String names, int tryCount) {
        RacingGame racingGame = new RacingGame(convertToNames(names));

        racingGame.moveCars(new TryCount(tryCount));

        String winners = decideWinners(racingGame);
        List<CarDto> resultCars = getResultCars(racingGame);

        jdbcTemplateDAO.saveResult(new GameResultDto(tryCount, winners, resultCars));

        return new GameResultResponseDto(winners, resultCars);
    }

    private List<Name> convertToNames(String inputNames) {
        return Arrays.stream(inputNames.split(","))
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private String decideWinners(RacingGame racingGame) {
        return racingGame.decideWinners().getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    private List<CarDto> getResultCars(RacingGame racingGame) {
        List<CarDto> carDtoList = new ArrayList<>();

        for (Car car : racingGame.getCars()) {
            carDtoList.add(new CarDto(car.getName(), car.getPosition()));
        }

        return carDtoList;
    }
}
