package racingcar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

public class RacingConsoleDao implements RacingDao {

    private static final Map<Integer, CarDto> carDtoRepository = new HashMap<>();
    private static final Map<Integer, TrackDto> trackDtoRepository = new HashMap<>();

    @Override
    public void saveCar(CarDto carDto) {
        Integer carDtoId = generateCarId();
        carDtoRepository.put(carDtoId, carDto);
    }

    @Override
    public void saveWithBatch(List<CarDto> carDtos) {
        for (CarDto carDto : carDtos) {
            Integer carDtoId = generateCarId();
            carDtoRepository.put(carDtoId, carDto);
        }
    }

    @Override
    public Integer saveTrack(TrackDto trackDto) {
        Integer trackDtoId = generateTrackId();
        trackDtoRepository.put(trackDtoId, trackDto);
        return trackDtoId;
    }

    @Override
    public List<Integer> findAllTrackIds() {
        return new ArrayList<>(trackDtoRepository.keySet());
    }

    @Override
    public Cars findAllCarsByTrackId(int trackId) {
        List<Car> cars = carDtoRepository.values()
                .stream()
                .map(carDto -> new Car(carDto.getName(), carDto.getPosition()))
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    private Integer generateCarId() {
        return carDtoRepository.size() + 1;
    }

    private Integer generateTrackId() {
        return trackDtoRepository.size() + 1;
    }
}
