package racingcar.service.dto;

import racingcar.entity.PlayerResult;
import racingcar.repository.dto.GetPlayerResultQueryResponseDto;

import java.util.ArrayList;
import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResponse> racingCars;

    private GameResponseDto(final String winners, final List<PlayerResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponseDto createByPlayerResult(final String winners, final List<PlayerResult> playerResults) {
        final List<PlayerResponse> racingCars = new ArrayList<>();
        for (PlayerResult playerResult : playerResults) {
            racingCars.add(PlayerResponse.createByPlayerResult(playerResult));
        }
        return new GameResponseDto(winners, racingCars);
    }

    public static GameResponseDto createByQueryResponse(
            final String winners, final List<GetPlayerResultQueryResponseDto> queryResponses) {
        final List<PlayerResponse> racingCars = new ArrayList<>();
        for (GetPlayerResultQueryResponseDto queryResponse : queryResponses) {
            racingCars.add(PlayerResponse.createByQueryResponse(queryResponse));
        }
        return new GameResponseDto(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResponse> getRacingCars() {
        return racingCars;
    }

    private static class PlayerResponse {
        private final String name;
        private final int position;

        private PlayerResponse(final String name, final int position) {
            this.name = name;
            this.position = position;
        }

        public static PlayerResponse createByPlayerResult(final PlayerResult playerResult) {
            return new PlayerResponse(playerResult.getName(), playerResult.getFinalPosition());
        }

        public static PlayerResponse createByQueryResponse(final GetPlayerResultQueryResponseDto queryResponse) {
            return new PlayerResponse(queryResponse.getName(), queryResponse.getFinalPosition());
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    }
}
