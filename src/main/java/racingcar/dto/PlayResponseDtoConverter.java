package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResponseDtoConverter {

    public static PlayResponseDto from(List<JudgedCarDto> judgedCars) {
        List<CarDto> racingCars = CarDto.convert(judgedCars);
        String winners = extractWinnerNames(judgedCars);
        return new PlayResponseDto(racingCars, winners);
    }

    private static String extractWinnerNames(final List<JudgedCarDto> judgedCars) {
        return judgedCars.stream()
                .filter(JudgedCarDto::isWinner)
                .map(JudgedCarDto::getName)
                .collect(Collectors.joining(", "));
    }
}
