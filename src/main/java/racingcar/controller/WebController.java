package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.UpdatingDAO;
import racingcar.domain.Cars;
import racingcar.dto.*;
import racingcar.utils.Converter;
import racingcar.utils.RandomNumberGenerator;
import racingcar.domain.vo.Trial;

import java.util.List;

@RestController
public class WebController {

    final private UpdatingDAO updatingDAO;

    public WebController(UpdatingDAO updatingDAO) {
        this.updatingDAO = updatingDAO;
    }

    @PostMapping("/plays")
    public FinalResultDto run(@RequestBody RequestBodyDTO dto) {
        Cars cars = Cars.initialize(dto.getNames(), RandomNumberGenerator.makeInstance());
        Trial trial = getTrial(dto.getCount());
        Cars updatedCars = playGame(cars, trial);
        final int racingId = updatingDAO.insert(trial);
        List<CarDto> carDtos = cars.getCarDtos();
        List<String> winnerNames = cars.getWinnerNames();

        for (CarDto car : carDtos) {
            String name = car.getName();
            updatingDAO.insert(
                new CarInfoDto(racingId, name, car.getPosition(), winnerNames.contains(name)));
        }
        return new FinalResultDto(updatedCars.getWinnerNames(), updatedCars.getCarDtos());
    }

    public Trial getTrial(String input) {
        return Trial.of(Converter.convertStringToLong(input));
    }

    private Cars playGame(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
        return cars;
    }
}
