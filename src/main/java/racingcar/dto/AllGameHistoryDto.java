package racingcar.dto;

import java.util.List;

public class AllGameHistoryDto {

    private final List<OneGameHistoryDto> oneGameHistoryDtoList;

    public AllGameHistoryDto(List<OneGameHistoryDto> oneGameHistoryDtoList) {
        this.oneGameHistoryDtoList = oneGameHistoryDtoList;
    }

    public List<OneGameHistoryDto> getOneGameHistoryDtoList() {
        return oneGameHistoryDtoList;
    }
}
