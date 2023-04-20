package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.RacingDAO;
import racingcar.domain.Cars;
import racingcar.domain.vo.Trial;
import racingcar.entity.CarInfoEntity;
import racingcar.dto.CarResponse;
import racingcar.dto.RacingResultResponse;

@Service
public class WebService implements RacingService {

    final private RacingDAO racingDAO;

    public WebService(RacingDAO racingDAO) {
        this.racingDAO = racingDAO;
    }

    @Override
    public Cars run(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
        save(cars);
        return cars;
    }

    private void save(Cars cars) {
        final int racingId = racingDAO.insert();

        for (CarResponse car : cars.getCarDtos()) {
            String name = car.getName();
            racingDAO.insert(
                new CarInfoEntity(0, racingId, name, car.getPosition(),
                    cars.getWinnerNames().contains(name)));
        }
    }

    public List<RacingResultResponse> loadHistory() {
        return racingDAO.findRacingIds().stream().map((id) ->
            new RacingResultResponse(racingDAO.findWinnersByRacingId(id),
                racingDAO.findCarsById(id).getCarDtos())
        ).collect(Collectors.toList());
    }


}
