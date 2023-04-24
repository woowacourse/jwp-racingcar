package racingcar.dto;

import racingcar.utils.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameInfoForRequest {
    private final String names;
    private final Integer count;

    public GameInfoForRequest(String names, Integer count) throws IllegalArgumentException {
        List<String> players = Arrays.stream(names.split(",")).collect(Collectors.toList());
        Validator.validateNullNames(names);
        Validator.validateNullcount(count);
        Validator.validatePlayerSize(players);
        Validator.validateProperNamePattern(players);
        Validator.validateNameSize(players);
        Validator.validateCountSize(count);
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
