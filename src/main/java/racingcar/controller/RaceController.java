package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.utils.NumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class RaceController {

    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;
    private Race race;

    public RaceController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    public void play() {
        readyForGame();
        playGame();
        printWinners();
        printParticipants();
    }

    private void readyForGame() {
        List<String> carNames = repeat(inputView::readCarNames);
        int count = repeat(inputView::readCount);
        race = new Race(count, carNames, numberGenerator);
    }

    private void playGame() {
        while (!race.isFinished()) {
            playRound();
        }
    }

    private void playRound() {
        race.playRound();
    }

    private void printWinners() {
        List<Car> winners = race.findWinners();
        outputView.printWinners(winners);
    }

    private void printParticipants() {
        List<Car> participants = race.getParticipants();
        outputView.printParticipantsInfo(participants);
    }

    private <T> T repeat(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return repeat(inputReader);
        }
    }
}
