package racingcar.service;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameDto;
import racingcar.repository.RacingCarRepository;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RacingCarService {

    private final Cars cars;
    private final NumberGenerator numberGenerator;
    private final RacingCarRepository racingCarRepository;

    public RacingCarService(Cars cars, NumberGenerator numberGenerator, RacingCarRepository racingCarRepository) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
        this.racingCarRepository = racingCarRepository;
    }

    public void play(int count) {
        move(count);
        racingCarRepository.save(new RacingGameDto(getWinners(), count), carsToPlayerDtos());
    }

    private void move(int count) {
        for (int i = 0; i < count; i++) {
            cars.moveAll(numberGenerator);
        }
    }

    public String getWinners() {
        return cars.pickWinners().getAll().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    private List<PlayerDto> carsToPlayerDtos() {
        return getCars().stream()
                .map(car -> new PlayerDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars.getAll();
    }
}
