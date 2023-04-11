package racing.controller.dto.response;

public class RacingCarStateResponse {
    private String name;
    private int position;

    public RacingCarStateResponse() {
    }

    public RacingCarStateResponse(String name, int position) {
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
