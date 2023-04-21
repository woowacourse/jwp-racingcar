package racingcar.dto;

import java.util.List;

public class ResultDTO {

    private List<String> winners;
    private List<CarDTO> carDTOs;

    public ResultDTO() {
    }

    public ResultDTO(final List<String> winners, final List<CarDTO> carDTOs) {
        this.winners = winners;
        this.carDTOs = carDTOs;
    }

    public List<String> getWinners(){
        return winners;
    }

    public List<CarDTO> getCarDTOs() {
        return carDTOs;
    }
}
