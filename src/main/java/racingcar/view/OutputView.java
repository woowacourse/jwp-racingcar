package racingcar.view;

import racingcar.domain.result.GameResultOfCar;
import racingcar.dto.CarDTO;
import racingcar.dto.RacingGameResponseDTO;
import racingcar.view.message.Message;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final int DEFAULT_ROUND = 0;
    private static final String ENTER_LINE = System.lineSeparator();

    public void printCarNameInputGuide() {
        print(Message.CAR_NAME_INPUT_GUIDE.getMessage());
    }

    public void printGameRoundGuide() {
        print(Message.GAME_ROUND_INPUT_GUIDE.getMessage());
    }

    private void printResultGuide() {
        print(Message.GAME_RESULT_GUIDE.getMessage());
    }

    private int getFirstGameRound(final List<GameResultOfCar> gameResultOfAllCars) {
        return gameResultOfAllCars.stream()
                .map(GameResultOfCar::getGameRound)
                .min(Integer::compareTo)
                .orElse(DEFAULT_ROUND);
    }

    private void printBlankLineBy(final int gameRound, final GameResultOfCar gameResultOfCar) {
        if (gameRound != gameResultOfCar.getGameRound()) {
            print(Message.EMPTY_MESSAGE.getMessage());
        }
    }

    private String makeResultMessage(final String carName, final int position) {
        return String.format(Message.RESULT_DELIMITER.getMessage(), carName, makePositionMessage(position));
    }

    private String makePositionMessage(final int position) {
        String positionMarker = Message.POSITION_MARKER.getMessage();
        return positionMarker.repeat(position);
    }

    public void printWinners(final List<GameResultOfCar> gameResultOfWinners) {
        print(makeWinnersMessage(gameResultOfWinners, Message.WINNER_DELIMITER.getMessage()));
    }

    private String makeWinnersMessage(final List<GameResultOfCar> gameResultOfWinners, final String delimiter) {
        return String.format(Message.WINNER.getMessage(), makeWinnerNames(gameResultOfWinners, delimiter));
    }

    private String makeWinnerNames(final List<GameResultOfCar> gameResultOfWinners, final String delimiter) {
        return gameResultOfWinners.stream()
                .map(GameResultOfCar::getCarName)
                .collect(Collectors.joining(delimiter));
    }

    private void print(final String message) {
        System.out.println(message);
    }

    public void printResult(final RacingGameResponseDTO racingGameResponseDTO) {
        System.out.println(Message.WINNER.getMessage() + racingGameResponseDTO.getWinners() + ENTER_LINE);
        System.out.println(Message.GAME_RESULT_GUIDE.getMessage());
        for (CarDTO carDTO : racingGameResponseDTO.getRacingCarDTOs()) {
            System.out.println("이름: " + carDTO.getName());
            System.out.println("최종 결과: " + carDTO.getPosition());
        }
    }
}
