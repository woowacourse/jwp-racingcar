package racing.web.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RacingCarStateResponse {

    private final String name;
    private final int position;

    @JsonCreator
    public RacingCarStateResponse(
            @JsonProperty("name") String name,
            @JsonProperty("position") int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
