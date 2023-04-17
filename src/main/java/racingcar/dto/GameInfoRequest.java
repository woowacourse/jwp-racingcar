package racingcar.dto;

import racingcar.exception.PlayerNumberException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameInfoRequest {
    private static final int MAX_PLAYER_NUM = 10;
    private final String names;
    private final Integer count;

    public GameInfoRequest(String names, Integer count) throws PlayerNumberException, PlayerSizeException {
        List<String> players = Arrays.stream(names.split(",")).collect(Collectors.toList());
        if (players.size() > MAX_PLAYER_NUM) {
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
