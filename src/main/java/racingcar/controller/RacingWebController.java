package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.RacingDao;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.car.strategy.RandomMovingStrategy;
import racingcar.model.track.Track;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingWebController {

    MovingStrategy movingStrategy = new RandomMovingStrategy();

    private final RacingDao racingDao;

    public RacingWebController(final RacingDao racingDao) {
        this.racingDao = racingDao;
    }

    @PostMapping("/plays")
    public ResponseEntity<TrackResponse> plays(@RequestBody TrackRequest trackRequest) {
        final String names = trackRequest.getNames();
        final Cars cars = makeCars(names, movingStrategy);

        final String trialTimes = trackRequest.getCount();
        final Track track = makeTrack(cars, trialTimes);
        startRace(track);

        // 이 때 저장해야 함
        final int trackId = racingDao.save(new TrackDto(Integer.parseInt(trialTimes)));

        final List<Car> winnerCars = cars.getWinnerCars();
        final String winnerCarNames = winnerCars.stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(", "));

        final List<Car> carsCurrentInfo = cars.getCarsCurrentInfo();
        // 자동차 저장
        for (Car car : carsCurrentInfo) {
            racingDao.save(new CarDto(car.getCarName(), car.getPosition(), winnerCars.contains(car), trackId));
        }
        final List<CarResponse> results = carsCurrentInfo.stream()
                .map(car -> new CarResponse(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(new TrackResponse(winnerCarNames, results));
    }

    private Cars makeCars(String name, final MovingStrategy movingStrategy) {
        return new Cars(name, movingStrategy);
    }

    private Track makeTrack(final Cars cars, String trialTimes) {
        return new Track(cars, trialTimes);
    }

    public void startRace(final Track track) {
        while (track.runnable()) {
            track.race();
        }
    }
}
