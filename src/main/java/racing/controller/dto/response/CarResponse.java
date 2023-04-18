package racing.controller.dto.response;

public class CarResponse {

    private String name;
    private int position;

    public CarResponse() {
    }

    public CarResponse(String name, int position) {
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
