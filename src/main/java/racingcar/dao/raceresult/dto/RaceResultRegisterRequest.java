package racingcar.dao.raceresult.dto;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.Car;
import racingcar.domain.RacingCars;

public class RaceResultRegisterRequest {

    private static final String CAR_NAMES_DELIMITER = ",";

    private final int trialCount;
    private final String winners;

    private RaceResultRegisterRequest(final int trialCount, final String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public static RaceResultRegisterRequest create(int trialCount, RacingCars racingCars) {
        List<String> winners = mapToNameFrom(racingCars);
        String joinedWinners = String.join(CAR_NAMES_DELIMITER, winners);
        return new RaceResultRegisterRequest(trialCount, joinedWinners);
    }

    private static List<String> mapToNameFrom(RacingCars racingCars) {
        return racingCars.getWinners()
                .stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }
}
