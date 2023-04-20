package racingcar.view;

import racingcar.domain.result.GameResultOfCar;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printCarNameInputGuide() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public void printGameRoundGuide() {
        System.out.println("시도할 횟수는 몇 회인가요?");
    }

    public void printRacingResult(final List<GameResultOfCar> winnerResults, final List<GameResultOfCar> allCarsResults) {
        System.out.println("실행 결과");
        System.out.println("우승자 : " + makeWinnerNames(winnerResults));
        for (GameResultOfCar car : allCarsResults) {
            System.out.println("이름 : " + car.getCarName());
            System.out.println("위치 : " + car.getPosition());
        }
    }

    private String makeWinnerNames(final List<GameResultOfCar> winnerResults) {
        return winnerResults.stream()
                .map(GameResultOfCar::getCarName)
                .collect(Collectors.joining(","));
    }
}
