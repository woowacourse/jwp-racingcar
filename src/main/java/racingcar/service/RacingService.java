package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.InsertingDAO;
import racingcar.dao.QueryingDAO;
import racingcar.domain.Cars;
import racingcar.dto.CarInfoEntity;
import racingcar.dto.CarResponse;
import racingcar.dto.RacingResultResponse;

@Service
public class RacingService {

    final private InsertingDAO insertingDAO;
    final private QueryingDAO queryingDAO;

    public RacingService(InsertingDAO insertingDAO, QueryingDAO queryingDAO) {
        this.insertingDAO = insertingDAO;
        this.queryingDAO = queryingDAO;
    }

    public void save(Cars cars) {
        final int racingId = insertingDAO.insert();

        for (CarResponse car : cars.getCarDtos()) {
            String name = car.getName();
            insertingDAO.insert(
                new CarInfoEntity(racingId, name, car.getPosition(),
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
