package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResponseDtoConverter {

    public static PlayResponseDto of(List<CarDto> racedCars) {
        List<CarResponseDto> carResponseDtos = CarDtoConverter.from(racedCars);
        String winners = extractWinnerNames(racedCars);
        return new PlayResponseDto(carResponseDtos, winners);
    }

    private static String extractWinnerNames(final List<CarDto> racedCars) {
        return racedCars.stream()
                .filter(CarDto::isWinner)
                .map(CarDto::getName)
                .collect(Collectors.joining(", "));
    }
}
