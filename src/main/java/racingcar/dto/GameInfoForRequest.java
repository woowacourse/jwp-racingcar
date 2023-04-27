package racingcar.dto;

import racingcar.utils.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameInfoForRequest {
    private final String names;
    private final Integer count;

    public GameInfoForRequest(String names, Integer count) throws IllegalArgumentException {
        List<String> players = Arrays.stream(names.split(",")).collect(Collectors.toList());
        InputValidator.validateNullNames(names);
        InputValidator.validateNullCount(count);
        InputValidator.validatePlayerSize(players);
        InputValidator.validateProperNamePattern(players);
        InputValidator.validateNameSize(players);
        InputValidator.validateCountSize(count);
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
