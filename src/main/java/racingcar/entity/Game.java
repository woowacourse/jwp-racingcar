package racingcar.entity;

import racingcar.dto.request.GameSaveDto;

import java.util.Objects;

public class Game {

    private final long id;
    private final int trialCount;
    private final String winners;

    public Game(final long id, final GameSaveDto gameSaveDto) {
        this.id = id;
        this.trialCount = gameSaveDto.getTrialCount();
        this.winners = gameSaveDto.getWinners();
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }
}
