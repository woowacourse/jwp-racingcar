package racingcar.dao.car;

import racingcar.dto.CarDto;
import racingcar.dto.WinnerDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemoryCarDao implements CarDao {
    private static final Map<Long, CarDto> car = new LinkedHashMap<>();
    private static long id = 1L;
    
    @Override
    public void save(final CarDto carDto) {
        car.put(id++, carDto);
    }
    
    @Override
    public long findIdByCarDto(final CarDto carDto) {
        return car.entrySet().stream()
                .filter(entry -> entry.getValue().equals(carDto))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 데이터입니다."));
    }
    
    @Override
    public List<CarDto> findCarDtosByWinnerDtos(final List<WinnerDto> winnerDtos) {
        return winnerDtos.stream()
                .map(WinnerDto::getCarId)
                .map(car::get)
                .collect(Collectors.toUnmodifiableList());
    }
    
    @Override
    public List<CarDto> findCarDtosByGameId(final long gameId) {
        final List<Long> sortedCarIds = getSortedCarIds();
        
        return sortedCarIds.stream()
                .map(car::get)
                .filter(carDto -> carDto.getGameId() == gameId)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private List<Long> getSortedCarIds() {
        return car.keySet().stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }
    
    public void deleteAll() {
        id = 1L;
        car.clear();
    }
}
