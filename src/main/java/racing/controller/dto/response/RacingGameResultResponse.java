package racing.controller.dto.response;

import java.util.List;

public class RacingGameResultResponse {

    private String winners;
    private List<RacingCarStateResponse> racingCars;

    public RacingGameResultResponse() {
    }

    public RacingGameResultResponse(List<String> winners, List<RacingCarStateResponse> racingCars) {
        this.winners = String.join("", winners);
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarStateResponse> getRacingCars() {
        return racingCars;
    }
}
