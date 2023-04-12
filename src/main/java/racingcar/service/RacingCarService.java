package racingcar.service;


import org.springframework.stereotype.Service;
import racingcar.dao.PlayerResultDAO;
import racingcar.dao.PlayersInfoDAO;
import racingcar.domain.Cars;

@Service
public class RacingCarService {

    private final PlayerResultDAO playerResultDAO;
    private final PlayersInfoDAO playersInfoDAO;

    public RacingCarService(PlayerResultDAO playerResultDAO, PlayersInfoDAO playersInfoDAO) {
        this.playerResultDAO = playerResultDAO;
        this.playersInfoDAO = playersInfoDAO;
    }

    public void saveResult(int trialCount, Cars cars){
        int tableId = playerResultDAO.returnTableIdAfterInsert(trialCount, cars.findWinners());
        playersInfoDAO.insert(tableId, cars.getCars());
    }
}
