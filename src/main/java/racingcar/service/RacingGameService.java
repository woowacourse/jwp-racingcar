package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayersResultDao;
import racingcar.domain.RacingGame;
import racingcar.dto.PlayerResultDto;
import racingcar.dto.RacingGameInputDto;
import racingcar.dto.RacingGameDto;
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

        RacingGameDto racingGameDto = racingGame.convertToDto();
        int resultId = playResultDao.insertResult(racingGame.convertToDto());
        playersResultDao.insertResult(racingGameDto.getRacingCars(), resultId);

        RacingGameResultDto racingGameResultDto = racingGame.convertToResultDto();
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
        List<PlayerResultDto> playerResultDtos = new ArrayList<>();
        for (PlayerResultEntity playerResultEntity : playerResultEntities) {
            playerResultDtos.add(new PlayerResultDto(playerResultEntity.getName(), playerResultEntity.getPosition()));
        }
        return new RacingGameResultDto(winners, playerResultDtos);
    }
}
