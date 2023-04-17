package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.InsertingDAO;
import racingcar.dao.QueryingDAO;
import racingcar.domain.Cars;
import racingcar.dto.*;
import racingcar.utils.Converter;
import racingcar.utils.RandomNumberGenerator;
import racingcar.domain.vo.Trial;

import java.util.List;

@RestController
public class WebController {

    final private InsertingDAO insertingDAO;
    final private QueryingDAO queryingDAO;

    public WebController(InsertingDAO insertingDAO, QueryingDAO queryingDAO) {
        this.insertingDAO = insertingDAO;
        this.queryingDAO = queryingDAO;
    }

    @PostMapping("/plays")
    public RacingResultResponse run(@RequestBody RacingRequest dto) {
        Cars cars = Cars.initialize(dto.getNames(), RandomNumberGenerator.makeInstance());
        Trial trial = getTrial(dto.getCount());
        Cars updatedCars = playGame(cars, trial);
        final int racingId = insertingDAO.insert(trial);
        List<CarResponse> carResponses = cars.getCarDtos();
        List<String> winnerNames = cars.getWinnerNames();

        for (CarResponse car : carResponses) {
            String name = car.getName();
            insertingDAO.insert(
                new CarInfoEntity(racingId, name, car.getPosition(), winnerNames.contains(name)));
        }
        return new RacingResultResponse(updatedCars.getWinnerNames(), updatedCars.getCarDtos());
    }

    public Trial getTrial(String input) {
        return Trial.of(Converter.convertStringToInt(input));
    }

    private Cars playGame(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
        return cars;
    }

    @GetMapping("/plays")
    public String run() {
        System.out.println(String.join(", ", queryingDAO.findWinnersByRacingId(1)));
        return String.join(", ", queryingDAO.findWinnersByRacingId(1));
    }
}
