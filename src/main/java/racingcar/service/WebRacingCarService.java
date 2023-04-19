package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.request.RacingStartRequest;
import racingcar.dto.response.RacingCarResponse;
import racingcar.dto.response.RacingResultResponse;
import racingcar.repository.WebRacingCarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebRacingCarService {

    private final WebRacingCarRepository webRacingCarRepository;

    public WebRacingCarService(WebRacingCarRepository webRacingCarRepository) {
        this.webRacingCarRepository = webRacingCarRepository;
    }

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

        saveGameResult(racingGame);

        return createRacingResultResponse(racingGame);
    }

    private void saveGameResult(RacingGame racingGame) {
        webRacingCarRepository.saveRacingGame(racingGame);
    }

    private RacingResultResponse createRacingResultResponse(RacingGame racingGame) {
        String winners = getWinners(racingGame);
        List<RacingCarResponse> racingCarResponses = getRacingCarResponses(racingGame);

        return new RacingResultResponse(winners, racingCarResponses);
    }

    public List<RacingResultResponse> inquireHistory() {
        List<RacingResultResponse> racingResultResponses = new ArrayList<>();

        for (RacingGame racingGame : webRacingCarRepository.findAllEndedRacingGame()) {
            racingResultResponses.add(new RacingResultResponse(
                    getWinners(racingGame),
                    getRacingCarResponses(racingGame)));
        }
        return racingResultResponses;
    }

    private String getWinners(RacingGame racingGame) {
        return racingGame.findWinnerCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }

    private List<RacingCarResponse> getRacingCarResponses(RacingGame racingGame) {
        List<RacingCarResponse> racingCarResponses = new ArrayList<>();

        for (Car car : racingGame.getCars()) {
            racingCarResponses.add(RacingCarResponse.from(car));
        }
        return racingCarResponses;
    }
}
