package racingcar.domain;

import java.util.List;

import racingcar.dto.RacingCarDto;

public class RacingGameInfo {
    private List<String> winners;
    private int count;
    private List<RacingCarDto> racingCars;

    public void setWinners(List<String> winners) {
        this.winners = winners;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRacingCars(List<RacingCarDto> racingCars) {
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public int getCount() {
        return count;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
