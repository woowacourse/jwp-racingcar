package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResponseDtoConverter {

    public static PlayResponseDto from(final List<JudgedCarDto> judgedCars) {
        final List<CarDto> racingCars = CarDto.convert(judgedCars);
        final String winners = extractWinnerNames(judgedCars);
        return new PlayResponseDto(racingCars, winners);
    }

    private static String extractWinnerNames(final List<JudgedCarDto> judgedCars) {
        return judgedCars.stream()
                .filter(JudgedCarDto::isWinner)
                .map(JudgedCarDto::getName)
                .collect(Collectors.joining(", "));
    }
}
