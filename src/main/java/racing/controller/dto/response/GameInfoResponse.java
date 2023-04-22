package racing.controller.dto.response;

import racing.domain.Cars;

/**
 * @author 베베
 * @version 1.0.0
 * @since by 베베 on 2023. 04. 22.
 */
public class GameInfoResponse {

    private final Long gameId;
    private final Cars cars;

    public GameInfoResponse(Long gameId, Cars cars) {
        this.gameId = gameId;
        this.cars = cars;
    }

    public Long getGameId() {
        return gameId;
    }

    public Cars getCars() {
        return cars;
    }

}
