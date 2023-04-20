package racingcar.dto;

import java.util.List;

public class HistoryResponseDto {

    private final List<ResultResponseDto> resultResponseDtos;

    public HistoryResponseDto(List<ResultResponseDto> resultResponseDtos) {
        this.resultResponseDtos = List.copyOf(resultResponseDtos);
    }

    public List<ResultResponseDto> getResultResponseDtos() {
        return List.copyOf(resultResponseDtos);
    }
}
