package racingcar.dto.input;

import racingcar.domain.TryCount;

public class TryCountRequest {
    private final TryCount tryCount;

    public TryCountRequest(final TryCount tryCount) {
        this.tryCount = tryCount;
    }

    public TryCount getTryCount() {
        return tryCount;
    }
}
