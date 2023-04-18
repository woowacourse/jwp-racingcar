package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;

public class MemoryCarDao implements CarDao{
    private List<RacingCarResultDto> cars = new ArrayList<>();

    @Override
    public void saveAll(List<RacingCarResultDto> racingCarResultDtos) {
        for (RacingCarResultDto car : racingCarResultDtos) {
            cars.add(car);
        }
    }

    @Override
    public List<RacingCarDto> findCarsById(long gameId) {
        return cars.stream()
                .filter(it -> it.getGameId() == gameId)
                .map(it -> RacingCarDto.of(it.getName(), it.getPosition()))
                .collect(Collectors.toList());
    }
}
