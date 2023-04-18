package racingcar.view;

import racingcar.dto.ResultDto;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("실행 결과");
    }

    public static void printWinners(String response) {
        System.out.println(response + "가 최종 우승했습니다.");
    }

    public static void printRacingProgress(ResultDto response) {
        System.out.println(response);
    }

    public static void printExceptionMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }
}
