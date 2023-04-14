package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResultDto;
import racingcar.dto.request.RacingStartRequest;
import racingcar.dto.response.RacingCarResponse;
import racingcar.dto.response.RacingResultResponse;
import racingcar.repository.RacingCarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebRacingCarService implements RacingCarService {

    private final RacingCarRepository racingCarRepository;

    public WebRacingCarService(RacingCarRepository racingCarRepository) {
        this.racingCarRepository = racingCarRepository;
    }

    @Override
    public RacingResultResponse play(RacingStartRequest racingStartRequest) {
        Cars cars = generateCars(racingStartRequest.getNames());
        return playGame(cars, racingStartRequest.getCount());
    }

    private Cars generateCars(List<String> carNames) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        List<Car> carInstances = new ArrayList<>();
        for (String name : carNames) {
            carInstances.add(new Car(name, numberGenerator));
        }
        return new Cars(carInstances);
    }

    private RacingResultResponse playGame(Cars cars, int round) {
        RacingGame racingGame = new RacingGame(cars, round);
        while (!racingGame.isGameEnded()) {
            racingGame.playOneRound();
        }

        saveGameResult(round, racingGame);

        return createRacingResultResponse(racingGame);
    }

    private void saveGameResult(int round, RacingGame racingGame) {
        racingCarRepository.save(createRacingGameResultDto(round, racingGame));
    }

    private RacingGameResultDto createRacingGameResultDto(int round, RacingGame racingGame) {
        List<CarDto> carDtos = new ArrayList<>();

        List<Car> winnerCars = racingGame.findWinnerCars();
        List<Car> cars = racingGame.getCars();

        for (Car car : cars) {
            carDtos.add(new CarDto(car.getName(), car.getPosition(), winnerCars.contains(car)));
        }

        return new RacingGameResultDto(round, carDtos);
    }

    private RacingResultResponse createRacingResultResponse(RacingGame racingGame) {
        List<RacingCarResponse> racingCars = getRacingCarResponses(racingGame);
        String winners = getWinners(racingGame);

        return new RacingResultResponse(racingCars, winners);
    }

    private List<RacingCarResponse> getRacingCarResponses(RacingGame racingGame) {
        List<RacingCarResponse> racingCars = new ArrayList<>();

        for (Car car : racingGame.getCars()) {
            racingCars.add(new RacingCarResponse(car.getName(), car.getPosition()));
        }
        return racingCars;
    }

    private String getWinners(RacingGame racingGame) {
        return racingGame.findWinnerCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }
}
