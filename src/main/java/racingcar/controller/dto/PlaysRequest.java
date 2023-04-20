package racingcar.controller.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlaysRequest {

    private String names;
    private int count;

    public PlaysRequest() {
        //get은 Request가 없다
    }

    public PlaysRequest(String names, int count) {
        validateNames(names);
        this.names = names;
        this.count = count;
    }

    private void validateNames(String names) {
        if (names == null || names.isBlank()) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }

    public List<String> getNames() {
        String delimiter = ",";
        return Arrays.stream(names.split(delimiter, -1)).map(String::strip)
                .collect(Collectors.toList());
    }

    public int getCount() {
        return count;
    }
}
