package racingcar.view;

import racingcar.dto.RaceResponse;

public class OutputView {

    private static final String WINNER_MESSAGE = "우승자";
    private static final String RESULT_MESSAGE = "결과";
    private static final String CAR_STATUS = "Name: %s, Position: %d";

    public void printRaceResponse(final RaceResponse raceResponse) {
        System.out.println(WINNER_MESSAGE);
        System.out.println(raceResponse.getWinners());
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        raceResponse.getRacingCars().
            forEach(racingCar -> {
                System.out.printf(CAR_STATUS, racingCar.getName(), racingCar.getPosition());
                System.out.println();
            });
    }
}
