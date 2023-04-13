import racingcar.dao.RacingGameDao;
import racingcar.dto.GameResultDto;

class StubRacingGameDao implements RacingGameDao {
    @Override
    public int saveResult(GameResultDto resultDto) {
        return 1;
    }
}
