package racingcar.view;

import racingcar.dto.response.CarResponse;
import racingcar.dto.response.RacingGameResponse;

public class OutputView {

    private static final String EXCEPTION_MESSAGE_PREFIX = "[ERROR] ";

    public void printResultMessage(RacingGameResponse racingGameResponse) {
        System.out.println("------------우승자------------");
        System.out.println(racingGameResponse.getWinners());
        System.out.println("------------전체 결과------------");
        for (CarResponse carResponse : racingGameResponse.getCarResponses()) {
            System.out.println("이름 : " + carResponse.getName() + "  위치 : " + carResponse.getPosition());
        }
    }

    public void printExceptionMessage(String message) {
        System.out.println(EXCEPTION_MESSAGE_PREFIX + message);
    }
}
