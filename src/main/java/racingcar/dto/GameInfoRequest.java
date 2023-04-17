package racingcar.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameInfoRequest {
    private static final int MAX_PLAYER_NUM = 10;
    private final String names;
    private final Integer count;

    public GameInfoRequest(String names, Integer count) throws IllegalArgumentException{
        List<String> players = Arrays.stream(names.split(",")).collect(Collectors.toList());
        if (players.size() > MAX_PLAYER_NUM) {
            throw new IllegalArgumentException("플레이어 수는 10명으로 제한됩니다");
        }
        if (players.stream().anyMatch(player -> player.length() < 1 || player.length() > 5)) {
            throw new IllegalArgumentException("플레이어의 이름 길이는 1에서 5 사이여야 합니다");
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
