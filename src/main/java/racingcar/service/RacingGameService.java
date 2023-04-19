package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayersResultDao;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;
import racingcar.dto.RacingGameInputDto;
import racingcar.dto.RacingGameResultDto;
import racingcar.entity.PlayResultEntity;
import racingcar.entity.PlayerResultEntity;
import racingcar.utils.InputUtil;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;

@Service
public class RacingGameService {
    @Autowired
    private PlayResultDao playResultDao;

    @Autowired
    private PlayersResultDao playersResultDao;

    public RacingGameResultDto play(final RacingGameInputDto racingGameInputDto) {
        RacingGame racingGame = new RacingGame(InputUtil.splitNames(racingGameInputDto.getNames()),
                racingGameInputDto.getCount());
        racingGame.start();

        RacingGameDto racingGameDto = new RacingGameDto(racingGame);
        int resultId = playResultDao.insertResult(racingGameDto);
        playersResultDao.insertResult(racingGameDto.getRacingCars(), resultId);

        RacingGameResultDto racingGameResultDto = new RacingGameResultDto(racingGame);
        OutputView.printResult(racingGameResultDto);
        return racingGameResultDto;
    }

    public List<RacingGameResultDto> requestAllRacingGameResult() {
        List<RacingGameResultDto> racingGameResultDtos = new ArrayList<>();
        List<PlayResultEntity> playResultEntities = playResultDao.getResult();
        for (PlayResultEntity playResultEntity : playResultEntities) {
            int resultId = playResultEntity.getId();
            racingGameResultDtos.add(requestRacingGameResult(resultId, playResultEntity.getWinners()));
        }
        return racingGameResultDtos;
    }

    private RacingGameResultDto requestRacingGameResult(final int resultId, final String winners) {
        List<PlayerResultEntity> playerResultEntities = playersResultDao.getResultByResultId(resultId);
        List<CarDto> carDtos = new ArrayList<>();
        for (PlayerResultEntity playerResultEntity : playerResultEntities) {
            carDtos.add(new CarDto(playerResultEntity.getName(), playerResultEntity.getPosition()));
        }
        return new RacingGameResultDto(winners, carDtos);
    }
}
