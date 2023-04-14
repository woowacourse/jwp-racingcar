package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.JdbcTemplateDAO;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.response.GameResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private static final String DELIMITER = ",";

    private final JdbcTemplateDAO jdbcTemplateDAO;

    @Autowired
    public RacingGameService(JdbcTemplateDAO jdbcTemplateDAO) {
        this.jdbcTemplateDAO = jdbcTemplateDAO;
    }

    public GameResponseDto play(List<String> names, int tryCount) {
        RacingGame racingGame = new RacingGame(convertToNames(names));

        racingGame.moveCars(new TryCount(tryCount));

        String winners = decideWinners(racingGame);
        List<CarDto> resultCars = getResultCars(racingGame);

        jdbcTemplateDAO.saveResult(new GameResultDto(tryCount, winners, resultCars));

        return new GameResponseDto(winners, resultCars);
    }

    private List<Name> convertToNames(List<String> names) {
        return names.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private String decideWinners(RacingGame racingGame) {
        return racingGame.decideWinners().getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private List<CarDto> getResultCars(RacingGame racingGame) {
        List<CarDto> carDtoList = new ArrayList<>();

        for (Car car : racingGame.getCars()) {
            carDtoList.add(new CarDto(car.getName(), car.getPosition()));
        }

        return carDtoList;
    }
}
