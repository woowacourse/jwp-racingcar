package racingcar.view;

import racingcar.application.RacingGameService.CarDto;
import racingcar.controller.RacingGameController.PlayGameResponse;

import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String INDENT = "    ";

    public static void printWinners(final PlayGameResponse playGameResponse) {
        System.out.println("winners: " + playGameResponse.getWinners());
        System.out.println("racingCars: " + makeCarsMessage(playGameResponse));
    }

    private static String makeCarsMessage(final PlayGameResponse playGameResponse) {
        return playGameResponse.getRacingCars()
                .stream()
                .map(OutputView::makeCarMessage)
                .map(it -> INDENT + "{" + lineSeparator() + it + lineSeparator() + INDENT + "}")
                .collect(Collectors.joining(
                        "," + lineSeparator(),
                        "[" + lineSeparator(),
                        lineSeparator() + "]")
                );
    }

    private static String makeCarMessage(final CarDto it) {
        return INDENT.repeat(2) + "name: " + it.getName()
                + lineSeparator()
                + INDENT.repeat(2) + "position: " + it.getPosition();
    }
}
