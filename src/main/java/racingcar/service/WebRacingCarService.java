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
        // Todo : 중간 과정 출력을 위한 로직 제거 => racingGame안에서 전체 게임 진행을 할 수 있도록 구현 가능할 듯
        while (!racingGame.isGameEnded()) {
            racingGame.playOneRound();
        }

        saveGameResult(racingGame);

        return createRacingResultResponse(racingGame);
    }

    private void saveGameResult(RacingGame racingGame) {
        racingCarRepository.saveRacingGame(racingGame);
    }

    private RacingResultResponse createRacingResultResponse(RacingGame racingGame) {
        List<RacingCarResponse> racingCarResponses = getRacingCarResponses(racingGame);
        String winners = getWinners(racingGame);

        return new RacingResultResponse(racingCarResponses, winners);
    }

    private List<RacingCarResponse> getRacingCarResponses(RacingGame racingGame) {
        List<RacingCarResponse> racingCarResponses = new ArrayList<>();

        for (Car car : racingGame.getCars()) {
            racingCarResponses.add(RacingCarResponse.from(car));
        }
        return racingCarResponses;
    }

    private String getWinners(RacingGame racingGame) {
        return racingGame.findWinnerCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }
}
