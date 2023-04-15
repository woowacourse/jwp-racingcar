package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.PlayerDao;
import racingcar.dao.RaceDao;
import racingcar.dao.WinnerDao;
import racingcar.dto.GameInputDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RacingResultDto;
import racingcar.game.Game;
import racingcar.util.NumberGenerator;

@Service
public class RacingGameService {

    private final RaceDao raceDao;
    private final PlayerDao playerDao;
    private final WinnerDao winnerDao;

    public RacingGameService(RaceDao raceDao, PlayerDao playerDao, WinnerDao winnerDao) {
        this.raceDao = raceDao;
        this.playerDao = playerDao;
        this.winnerDao = winnerDao;
    }

    public RacingResultDto playGame(GameInputDto gameInputDto, NumberGenerator numberGenerator) {
        Game game = new Game(gameInputDto.getNames(), gameInputDto.getCount());
        game.playGameWithoutPrint(numberGenerator);
        RaceResultDto raceResultDto = new RaceResultDto(game);

        int raceId = raceDao.insert(gameInputDto);
        playerDao.insertAll(raceResultDto, raceId);
        List<Integer> winnerCarIds = playerDao.getWinnerCarIds(raceId, raceResultDto);
        winnerDao.insertAll(raceId, winnerCarIds);
        return new RacingResultDto(game.getWinners(), game.getCars());
    }
}
