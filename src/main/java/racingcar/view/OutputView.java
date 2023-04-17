package racingcar.view;

import racingcar.domain.game.GameResultOfCar;
import racingcar.dto.CarDTO;
import racingcar.dto.RacingGameResponseDTO;
import racingcar.view.message.Message;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final int DEFAULT_ROUND = 0;
    private static final String ENTER_LINE = System.lineSeparator();

    public void printCarNameInputGuide() {
        System.out.println(Message.CAR_NAME_INPUT_GUIDE.getMessage());
    }

    public void printGameRoundGuide() {
        System.out.println(Message.GAME_ROUND_INPUT_GUIDE.getMessage());
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
