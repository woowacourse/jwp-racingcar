package racingcar.database;

import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponseDto;
import racingcar.dto.response.RacingGameWinnersDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WebDatabase implements Database {

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;


    public WebDatabase(RacingGameDao racingGameDao, RacingCarDao racingCarDao) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }

    @Override
    public void save(RacingGame racingGame, int trialCount) {
        final Long gameId = saveGame(racingGame, trialCount);
        saveCars(racingGame, gameId);
    }


    private Long saveGame(final RacingGame racingGame, final int trialCount) {
        final String names = String.join(",", racingGame.findWinners());
        return racingGameDao.save(names, trialCount);
    }

    private void saveCars(final RacingGame racingGame, final Long gameId) {
        for (final Car car : racingGame.getCurrentResult()) {
            racingCarDao.save(gameId, car);
        }
    }

    public List<RacingGameResponseDto> findAllHistories() {
        List<RacingGameResponseDto> racingGameResponseDtos = new ArrayList<>();
        for (RacingGameWinnersDto racingGameWinnersDto : racingGameDao.findAllWinners()) {
            racingGameResponseDtos.add(
                    new RacingGameResponseDto(
                            racingGameWinnersDto.getWinners(), racingCarDao.findCars(racingGameWinnersDto.getId())
                    )
            );
        }
        return racingGameResponseDtos;
    }
}
