package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.PlayResponse;
import racingcar.dto.VehicleDto;
import racingcar.model.Cars;
import racingcar.model.Vehicle;
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

    public PlayResponse result(final long gameId, final Cars cars) {
        List<String> winners = findWinnerName(cars);
        List<VehicleDto> racingCars = findAllVehicle(cars);
        for (Vehicle car : cars.getCars()) {
            recordDao.insert(gameId, winners.contains(car.getName()), car);
        }
        return new PlayResponse(winners, racingCars);
    }

    private List<String> findWinnerName(final Cars cars) {
        return cars.getWinner().stream()
                .map(Vehicle::getName)
                .collect(Collectors.toList());
    }

    private List<VehicleDto> findAllVehicle(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(car -> new VehicleDto(car.getName(), car.getDistance()))
                .collect(Collectors.toList());
    }
}
