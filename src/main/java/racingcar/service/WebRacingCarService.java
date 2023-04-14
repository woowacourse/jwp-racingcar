package racingcar.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.Map;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.CarResultDto;
import racingcar.dto.RacingGameResultDto;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.CarResponse;
import racingcar.dto.response.RacingGameResultResponse;
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
    public RacingGameResultResponse play(RacingGameRequest racingGameRequest) {
        Cars cars = generateCars(racingGameRequest.getNames());
        return playGame(cars, racingGameRequest.getCount());
    }

    @Override
    public List<RacingGameResultResponse> findGameResults() {
        final List<CarResultDto> carResultDtos = racingCarRepository.findAll();

        final Map<Integer, List<CarResultDto>> eachResults = carResultDtos.stream()
                .collect(groupingBy(CarResultDto::getGameId));

        List<RacingGameResultResponse> racingResultResponses = new ArrayList<>();

        for (List<CarResultDto> carResultDtosById : eachResults.values()) {

            List<CarResponse> carResponses = carResultDtosById.stream()
                    .map(CarResponse::new)
                    .collect(Collectors.toList());

            final String winners = carResultDtosById.stream()
                    .filter(CarResultDto::isWin)
                    .map(CarResultDto::getName)
                    .collect(Collectors.joining(", "));

            racingResultResponses.add(new RacingGameResultResponse(carResponses, winners));
        }

        return racingResultResponses;
    }

    //TODO: Dto에서 수행하도록 변경
    private Cars generateCars(List<String> carNames) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        List<Car> carInstances = new ArrayList<>();
        for (String name : carNames) {
            carInstances.add(new Car(name, numberGenerator));
        }
        return new Cars(carInstances);
    }

    private RacingGameResultResponse playGame(Cars cars, int round) {
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
        List<CarResultDto> carResultDtos = new ArrayList<>();

        List<Car> winnerCars = racingGame.findWinnerCars();
        List<Car> cars = racingGame.getCars();

        for (Car car : cars) {
            carResultDtos.add(new CarResultDto(car.getName(), car.getPosition(), winnerCars.contains(car)));
        }

        return new RacingGameResultDto(round, carResultDtos);
    }

    private RacingGameResultResponse createRacingResultResponse(RacingGame racingGame) {
        List<CarResponse> racingCars = racingGame.getCars().stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());

        String winners = racingGame.findWinnerCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));

        return new RacingGameResultResponse(racingCars, winners);
    }
}
