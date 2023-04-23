package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.PlayResponseDto;

public class OutputView {

    public static void printInputCarNamesNotice() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public static void printInputTryTimesNotice() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public static void printResultNotice() {
        System.out.println("실행 결과");
    }

    public static void printWinner(PlayResponseDto responseDto) {
        System.out.println("우승자 : " + responseDto.getWinners());
        for (CarDto carDto : responseDto.getRacingCars()) {
            System.out.println("name : " + carDto.getName());
            System.out.println("position : "+ carDto.getPosition());
            System.out.println();
        }
    }
}
