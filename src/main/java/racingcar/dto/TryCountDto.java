package racingcar.dto;

import racingcar.domain.TryCount;

public class TryCountDto {
    private final int tryCount;

    public TryCountDto(TryCount tryCount) {
        this.tryCount = tryCount.getCount();
    }

    public int getTryCount() {
        return tryCount;
    }
}
