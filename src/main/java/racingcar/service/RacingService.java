package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.repository.CarInfoRepository;
import racingcar.repository.RaceRepository;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.RacingResultDto;
import racingcar.domain.entity.CarInfo;
import racingcar.domain.entity.Race;
import racingcar.utils.NumberGenerator;
import racingcar.domain.Name;
import racingcar.domain.Names;
import racingcar.domain.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacingService {

    private final NumberGenerator numberGenerator;
    private final RaceRepository raceRepository;
    private final CarInfoRepository carInfoRepository;

    public RacingService(NumberGenerator numberGenerator, RaceRepository raceRepository, CarInfoRepository carInfoRepository) {
        this.numberGenerator = numberGenerator;
        this.raceRepository = raceRepository;
        this.carInfoRepository = carInfoRepository;
    }

    public RacingResultDto race(String names, int count) {
        Cars cars = initializeCars(names);
        Trial trial = Trial.of(count);
        playGame(cars, trial);
        return new RacingResultDto(trial, cars.getWinnerNames(), cars.getCarDtos());
    }

    private Cars initializeCars(String names) {
        Names carNames = getCarNames(names);
        return saveCars(carNames);
    }

    private Names getCarNames(String names) {
        return Names.of(
                Name.of(Arrays.asList(names.split(",")))
        );
    }

    private void playGame(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
    }

    private Cars saveCars(Names names) {
        List<Car> cars = new ArrayList<>();
        names.nameIterator()
                .forEachRemaining(name -> cars.add(Car.of(name)));
        return new Cars(cars, numberGenerator);
    }

    public void saveResult(RacingResultDto racingResultDto) {
        final int raceId = raceRepository.saveRace(new Race(racingResultDto.getTrial()));
        for (CarDto car : racingResultDto.getRacingCars()) {
            String name = car.getName();
            CarInfo carInfo = new CarInfo(raceId, name, car.getPosition(), racingResultDto.isWinnerContaining(name));
            carInfoRepository.saveCar(carInfo);
        }
    }

    public List<RacingResultDto> findAllResults() {
        ArrayList<RacingResultDto> racingResults = new ArrayList<>();

        List<Integer> raceIds = raceRepository.findAllId();
        for (Integer raceId : raceIds) {
            List<CarInfo> carInfos = carInfoRepository.findAllByRaceId(raceId);

            List<String> winnerNames = carInfos.stream()
                    .filter(CarInfo::getIsWinner)
                    .map(CarInfo::getName)
                    .collect(Collectors.toList());

            List<CarDto> carDtos = carInfos.stream()
                    .map(CarDto::new)
                    .collect(Collectors.toList());

            racingResults.add(new RacingResultDto(null, winnerNames, carDtos));
        }

        return racingResults;
    }
}
