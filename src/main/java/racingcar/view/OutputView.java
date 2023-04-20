package racingcar.view;

import racingcar.service.dto.PlayerResultResponseDto;

import java.util.List;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String WINNER = "우승자: ";
    private static final String RESULT = "결과: ";
    private static final String NAME = "Name: ";
    private static final String POSITION = "Position: ";

    public static void printResultMessage() {
        System.out.println("실행 결과");
    }

    public static void printFinalResult(String winners) {
        System.out.println(WINNER + winners);
        System.out.println();
    }

    public static void printCarStatus(List<PlayerResultResponseDto> playerResultResponseDtos) {
        System.out.println(RESULT);
        playerResultResponseDtos.forEach(playerResultResponseDto -> {
            final String name = playerResultResponseDto.getName();
            final int position = playerResultResponseDto.getPosition();
            System.out.println(NAME + name + DELIMITER + POSITION + position);
        });
        System.out.println();
    }
}
