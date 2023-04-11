package racingcar.game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Participants;
import racingcar.domain.Race;
import racingcar.util.NumberGenerator;

public class Game {

    private static final int MOVE_DISTANCE = 1;
    private static final int MOVE_THRESHOLDS = 4;
    private final Race race;
    private final Participants participants;

    public Game(final String carNames, final String raceCount) {
        this.participants = makeParticipants(carNames);
        this.race = new Race(raceCount);
    }

    private Participants makeParticipants(final String carNames) {
        List<String> names = Arrays.asList(carNames.split(",", -1));
        return new Participants(names);
    }

    public void playGameWithoutPrint(NumberGenerator numberGenerator) {
        while (!race.isFinished()) {
            raceOneRoundWithoutPrint(numberGenerator);
        }
    }

    private void raceOneRoundWithoutPrint(NumberGenerator numberGenerator) {
        final List<Car> cars = participants.getCars();
        cars.forEach((car) -> driveOrNot(car, numberGenerator));
        race.addCount();
    }

    private void driveOrNot(Car car, NumberGenerator numberGenerator) {
        int number = numberGenerator.generate();
        if (!isEnoughToMove(number)) {
            car.drive(MOVE_DISTANCE);
        }
    }

    private boolean isEnoughToMove(final int score) {
        return score >= MOVE_THRESHOLDS;
    }

    public List<Car> getWinners() {
        List<Car> candidates = participants.getCars();
        final int maxDistance = candidates.stream()
            .mapToInt(Car::getPosition)
            .max()
            .orElse(0);
        return candidates.stream()
            .filter(car -> car.isWinner(maxDistance))
            .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return participants.getCars();
    }
//    public Game(RaceController raceController) {
//        this.raceController = raceController;
//    }

//    public void ready(InputView inputView) {
//        raceController.getParticipants(inputView);
//        raceController.getCount(inputView);
//    }

//    public void showResult(OutputView outputView) {
//        outputView.printResultMessage();
//        outputView.printWinners(raceController.getWinners());
//    }

//    private void raceOneRound(NumberGenerator numberGenerator, OutputView outputView) {
//        final List<Car> cars = participants.getCars();
//        cars.forEach((car) -> driveOrNot(car, numberGenerator));
//        race.isFinished();
//        raceController.printRoundResult(outputView);
//    }

//    private void raceOneRound(NumberGenerator numberGenerator) {
//        List<Car> cars = participants.getCars();
//        cars.forEach((car) -> driveOrNot(car, numberGenerator));
//        race.isFinished();
//    }

//    public void playGame(NumberGenerator numberGenerator, OutputView outputView) {
//        while (!race.isFinished()) {
//            raceOneRound(numberGenerator, outputView);
//        }
//        raceController.printRoundResult(outputView);
//    }
}
