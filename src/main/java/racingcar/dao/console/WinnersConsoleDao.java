package racingcar.dao.console;

import racingcar.dao.WinnersDao;
import racingcar.domain.Winner;
import racingcar.entity.WinnersEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinnersConsoleDao implements WinnersDao {

    private static WinnersConsoleDao winnersConsoleDao;

    private List<WinnersEntity> winners;

    private WinnersConsoleDao(List<WinnersEntity> winners) {
        this.winners = winners;
    }

    public static WinnersDao of() {
        if (!Objects.isNull(winnersConsoleDao)) {
            return winnersConsoleDao;
        }
        winnersConsoleDao = new WinnersConsoleDao(new ArrayList<>());
        return winnersConsoleDao;
    }

    @Override
    public List<Winner> getWinnerNamesByGameId(int gameId) {
        RacingCarConsoleDao racingCarConsoleDao = RacingCarConsoleDao.of();
        return winners.stream()
                .filter(winnersEntity -> winnersEntity.getGameId() == gameId)
                .map(winnersEntity -> new Winner(racingCarConsoleDao.findNameById(winnersEntity.getCarId())))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void saveWinners(int gameId, int carId) {
        WinnersEntity winnersEntity = new WinnersEntity.Builder()
                .gameId(gameId)
                .carId(carId)
                .build();
        winners.add(winnersEntity);
    }

}
