package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.InsertingDAO;
import racingcar.dao.QueryingDAO;
import racingcar.domain.Cars;
import racingcar.domain.vo.Trial;
import racingcar.entity.CarInfoEntity;
import racingcar.dto.CarResponse;
import racingcar.dto.RacingResultResponse;

@Service
public class WebService implements RacingService{

    final private InsertingDAO insertingDAO;
    final private QueryingDAO queryingDAO;

    public WebService(InsertingDAO insertingDAO, QueryingDAO queryingDAO) {
        this.insertingDAO = insertingDAO;
        this.queryingDAO = queryingDAO;
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
        final int racingId = insertingDAO.insert();

        for (CarResponse car : cars.getCarDtos()) {
            String name = car.getName();
            insertingDAO.insert(
                new CarInfoEntity(0, racingId, name, car.getPosition(),
                    cars.getWinnerNames().contains(name)));
        }
    }

    public List<RacingResultResponse> loadHistory() {
        return queryingDAO.findRacingIds().stream().map((id) ->
            new RacingResultResponse(queryingDAO.findWinnersByRacingId(id),
                queryingDAO.findCarsById(id).getCarDtos())
        ).collect(Collectors.toList());
    }


}
