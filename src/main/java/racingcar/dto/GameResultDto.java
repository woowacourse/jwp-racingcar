package racingcar.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GameResultDto {
    private final GameDto gameDto;
    private final List<CarDto> winnerCarDtos;
    private final List<CarDto> carDtos;
    
    public GameResultDto(final GameDto gameDto, final List<CarDto> winnerCarDtos, final List<CarDto> carDtos) {
        this.gameDto = gameDto;
        this.winnerCarDtos = winnerCarDtos;
        this.carDtos = carDtos;
    }
}
