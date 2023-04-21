package racing.web.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RacingGameResultResponse {

    private final String winners;
    private final List<RacingCarStateResponse> racingCars;

    @JsonCreator
    public RacingGameResultResponse(
            @JsonProperty("winners") String winners,
            @JsonProperty("racingCars") List<RacingCarStateResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarStateResponse> getRacingCars() {
        return racingCars;
    }
}
