package racingcar.controller.dto;

import java.util.List;

public class GameRecordDto {

    private List<GameResultDto> gameRecord;

    public GameRecordDto(List<GameResultDto> gameRecord) {
        this.gameRecord = gameRecord;
    }

    public List<GameResultDto> getGameRecord() {
        return gameRecord;
    }

    public void setGameRecord(List<GameResultDto> gameRecord) {
        this.gameRecord = gameRecord;
    }
}
