package racingcar.dto;

import java.util.List;

public class RacingGameFindDto {

    private final GameFindDto gameFindDto;
    private final List<PlayerFindDto> playerFindDto;

    public RacingGameFindDto(GameFindDto gameFindDto, List<PlayerFindDto> playerFindDto) {
        this.gameFindDto = gameFindDto;
        this.playerFindDto = playerFindDto;
    }

    public GameFindDto getGameFindDto() {
        return gameFindDto;
    }

    public List<PlayerFindDto> getPlayerFindDtos() {
        return playerFindDto;
    }
}
