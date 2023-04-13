package racingcar.controller.dto;

public class PlaysRequest {

    private final String names;
    private final int count;

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

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
