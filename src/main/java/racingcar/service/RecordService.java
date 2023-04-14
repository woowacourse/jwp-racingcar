package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.response.PlayResponse;
import racingcar.dto.VehicleDto;
import racingcar.domain.Cars;
import racingcar.domain.Vehicle;
import racingcar.repository.RecordDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {

    private final RecordDao recordDao;

    @Autowired
    public RecordService(final RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public void saveResults(final long gameId, final Cars cars) {
        List<String> winnerNames = findWinnerName(cars);

        for (final Vehicle car : cars.getCars()) {
            recordDao.insert(gameId, winnerNames.contains(car.getName()), car);
        }
    }

    public PlayResponse result(final Cars cars) {
        List<String> winnerNames = findWinnerName(cars);
        List<VehicleDto> racingCars = toVehicleDto(cars);

        return new PlayResponse(winnerNames, racingCars);
    }

    private List<String> findWinnerName(final Cars cars) {
        return cars.getWinner().stream()
                .map(Vehicle::getName)
                .collect(Collectors.toList());
    }

    private List<VehicleDto> toVehicleDto(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(car -> new VehicleDto(car.getName(), car.getDistance()))
                .collect(Collectors.toList());
    }
}
