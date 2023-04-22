package racingcar.database;

import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingCarResponseDto;
import racingcar.dto.response.RacingGameResponseDto;
import racingcar.dto.response.RacingGameWinnersDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WebDatabase implements Database {

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;


    public WebDatabase(RacingGameDao racingGameDao, RacingCarDao racingCarDao) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }

    @Override
    public void save(final RacingGame racingGame, final int trialCount) {
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

    @Override
    public List<RacingGameResponseDto> findAllHistories() {
        List<RacingGameResponseDto> racingGameResponseDtos = new ArrayList<>();
        List<RacingCarResponseDto> allRacingCarResponseDtos = racingCarDao.findAllCars();
        addRacingGameResponseDto(racingGameResponseDtos, allRacingCarResponseDtos);
        return racingGameResponseDtos;
    }

    private void addRacingGameResponseDto(
            final List<RacingGameResponseDto> racingGameResponseDtos,
            final List<RacingCarResponseDto> allRacingCarResponseDtos) {
        for (RacingGameWinnersDto racingGameWinnersDto : racingGameDao.findAllWinners()) {
            List<RacingCarResponseDto> racingCarResponseDtos = filterSameGameId(racingGameWinnersDto, allRacingCarResponseDtos);
            racingGameResponseDtos.add(
                    new RacingGameResponseDto(racingGameWinnersDto.getWinners(), racingCarResponseDtos)
            );
        }
    }

    private List<RacingCarResponseDto> filterSameGameId(
            final RacingGameWinnersDto racingGameWinnersDto,
            final List<RacingCarResponseDto> allRacingCarResponseDtos) {
        List<RacingCarResponseDto> racingCarResponseDtos = allRacingCarResponseDtos.stream()
                .filter(s -> s.getGameId().equals(racingGameWinnersDto.getId()))
                .collect(Collectors.toList());
        return racingCarResponseDtos;
    }
}
