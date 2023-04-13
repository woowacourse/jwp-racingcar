package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.UpdatingDAO;
import racingcar.domain.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.CarInfoDto;
import racingcar.dto.FinalResultDto;
import racingcar.dto.RequestBodyDTO;
import racingcar.utils.RacingUtil;
import racingcar.vo.Trial;

import java.util.List;

@RestController
public class WebController {


    final private UpdatingDAO updatingDAO;

    public WebController(UpdatingDAO updatingDAO){
        this.updatingDAO = updatingDAO;
    }

    @PostMapping("/plays")
    public FinalResultDto playRacing(@RequestBody RequestBodyDTO dto) {
        Cars cars = RacingUtil.race(dto.getNames(), dto.getCount());

        final int racingId = updatingDAO.insert(Trial.of(dto.getCount()));
        List<CarDto> carDtos = cars.getCarDtos();
        List<String> winnerNames = cars.getWinnerNames();

        for(CarDto car : carDtos){
            String name = car.getName();
            updatingDAO.insert(new CarInfoDto(racingId, name, car.getPosition(), winnerNames.contains(name)));
        }
        return new FinalResultDto(winnerNames, carDtos);
    }
}
