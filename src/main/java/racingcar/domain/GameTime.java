package racingcar.domain;

public class GameTime {

    private static final int MAX_GAME_TIME = 500;
    private static final int RANGE_BOUNDARY = 0;

    private final int gameTime;
    private int timeSpent = 0;

    public GameTime(final int gameTime) {
        validateMaxGameTime(gameTime);
        validatePositive(gameTime);
        this.gameTime = gameTime;
    }

    public GameTime(final String gameTime) {
        this(validateParsing(gameTime));
    }

    private static int validateParsing(final String gameTime) {
        try {
            Integer.parseInt(gameTime);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
        return Integer.parseInt(gameTime);
    }

    private void validatePositive(final int parsedGameTime) {
        if (parsedGameTime <= RANGE_BOUNDARY) {
            throw new IllegalArgumentException("[ERROR] 양수만 입력 가능합니다.");
        }
    }

    private void validateMaxGameTime(final int gameTimeParsed) {
        if (gameTimeParsed >= MAX_GAME_TIME) {
            throw new IllegalArgumentException("[ERROR] 500회 미만으로 입력해주세요.");
        }
    }

    public boolean isPlayable() {
        return gameTime != timeSpent;
    }

    public void runOnce() {
        timeSpent++;
    }

    public int getGameTime() {
        return gameTime;
    }
}
