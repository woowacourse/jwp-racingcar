package racingcar.dao.entity;

import racingcar.dto.RacingGameDto;

import java.util.Optional;

public class Game {

    private final Long gameId;
    private final int playCount;
    private final String winners;

    public Game(int playCount, String winners) {
        this.gameId = null;
        this.playCount = playCount;
        this.winners = winners;
    }

    public Game(RacingGameDto racingGameDto) {
        this(racingGameDto.getCount(), racingGameDto.getWinners());
    }

    public Optional<Long> getGameId() {
        return Optional.ofNullable(gameId);
    }

    public int getPlayCount() {
        return playCount;
    }

    public String getWinners() {
        return winners;
    }

}
