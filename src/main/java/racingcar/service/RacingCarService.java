package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.domain.Names;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;

@Service
public class RacingCarService {
    public List<RacingCarDto> createRacingCarDtos(final RacingCars racingCars) {
        return racingCars.getRacingCars()
            .stream()
            .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
            .collect(Collectors.toUnmodifiableList());
    }

    public RacingCars createRacingCar(RacingCarRequest racingCarRequest) {
        Names names = new Names(racingCarRequest.getNames());
        return new RacingCars(createRacingCar(names));
    }

    private List<RacingCar> createRacingCar(Names names) {
        return names.getNames()
            .stream()
            .map(RacingCar::new)
            .collect(Collectors.toList());
    }

    public void playGame(RacingCars racingCars, TryCount tryCount) {
        while (canProceed(tryCount)) {
            racingCars.moveAll();
            tryCount = tryCount.deduct();
        }
    }

    private boolean canProceed(TryCount tryCount) {
        return tryCount.isOpportunity();
    }
}
