package racingcar.dto;

import racingcar.dao.entity.CarEntity;
import racingcar.util.NameFormatConverter;

import java.util.ArrayList;
import java.util.List;

public class GameRecordResponseDto {
    private final String winners;
    private final List<CarEntity> racingCars;

    public GameRecordResponseDto(List<CarEntity> winners, List<CarEntity> racingCars) {
        this.winners = NameFormatConverter.joinNameWithDelimiter(convert(winners));
        this.racingCars = racingCars;
    }

    private List<String> convert(List<CarEntity> winners) {
        List<String> result = new ArrayList<>();
        for(CarEntity car : winners){
            result.add(car.getName());
        }
        return result;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarEntity> getRacingCars() {
        return racingCars;
    }
}
