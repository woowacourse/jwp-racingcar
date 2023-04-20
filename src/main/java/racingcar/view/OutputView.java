package racingcar.view;

import racingcar.dto.response.RacingCarResponseDto;
import racingcar.dto.response.RacingGameResponseDto;

import java.util.List;

import static java.text.MessageFormat.format;

public class OutputView {

    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String POSITION_MESSAGE_FORMAT = "{0} : {1}";
    private static final String WINNERS_MESSAGE_FORMAT = "{0}가 최종 우승했습니다.";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    public void printResult(RacingGameResponseDto racingGameResponseDto) {
        System.out.println(RESULT_MESSAGE);
        printCars(racingGameResponseDto.getRacingCars());
        printWinners(racingGameResponseDto);
    }

    private void printCars(List<RacingCarResponseDto> racingCarResponseDtos) {
        for (RacingCarResponseDto racingCarResponseDto : racingCarResponseDtos) {
            System.out.println(format(
                    POSITION_MESSAGE_FORMAT,
                    racingCarResponseDto.getName(),
                    racingCarResponseDto.getPosition()
            ));
        }
    }

    private void printWinners(RacingGameResponseDto racingGameResponseDto) {
        System.out.println(format(WINNERS_MESSAGE_FORMAT, racingGameResponseDto.getWinners()));
    }

    public void printErrorMessage(final String message) {
        System.out.println(ERROR_MESSAGE + message);
    }

}
