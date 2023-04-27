package racingcar.utils;

import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {
    private static final int MIN_PLAYER_NAME = 1;
    private static final int MAX_PLAYER_NAME = 5;
    private static final int MAX_PLAYER_NUM = 10;

    private InputValidator() {
        throw new AssertionError("인스턴스화할 수 없습니다");
    }

    public static void validateNullNames(String names) {
        if (names.isBlank()) {
            throw new IllegalArgumentException("값을 입력해야 합니다");
        }
    }

    public static void validateProperNamePattern(List<String> names) {
        for (String name : names) {
            if (!Pattern.matches("^[a-zA-z가-힣]*$", name)) {
                throw new IllegalArgumentException("사용자의 이름이 적절하게 입력되지 않았습니다");
            }
        }
    }

    public static void validateCountSize(int count) {
        if (count < 1 || count > 20) {
            throw new IllegalArgumentException("최소 1회 이상 최대 20회 이하로 실행해야 합니다");
        }
    }

    public static void validateNameSize(List<String> players) {
        if (players.stream().anyMatch(player -> player.length() < MIN_PLAYER_NAME || player.length() > MAX_PLAYER_NAME)) {
            throw new IllegalArgumentException("플레이어의 이름 길이는 1에서 5 사이여야 합니다");
        }
    }

    public static void validateNullCount(Integer count) {
        if (count == null) {
            throw new IllegalArgumentException("값을 입력해야 합니다");
        }
    }

    public static void validatePlayerSize(List<String> players) {
        if (players.size() > MAX_PLAYER_NUM || players.size() < 1) {
            throw new IllegalArgumentException("플레이어 수는 1~10명으로 제한됩니다");
        }
    }
}
