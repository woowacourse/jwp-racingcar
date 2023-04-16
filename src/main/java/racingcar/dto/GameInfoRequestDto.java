package racingcar.dto;

import racingcar.exception.PlayerNumberException;

public class GameInfoRequestDto {
    private static final int MAX_PLAYER_NUM = 10;
    private final String names;
    private final Integer count;

    public GameInfoRequestDto(String names, Integer count) throws PlayerNumberException {
        if (names.split(",").length > MAX_PLAYER_NUM) {
            throw new PlayerNumberException("플레이어 수는 10명으로 제한됩니다");
        }
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
