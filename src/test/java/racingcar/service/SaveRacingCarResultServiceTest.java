package racingcar.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;
import racingcar.repository.dao.GameDao;
import racingcar.repository.dao.GameUsersPositionDao;
import racingcar.repository.dao.GameWinUsersDao;
import racingcar.repository.dao.UsersDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.GameUsersPositionEntity;
import racingcar.repository.entity.GameWinUsersEntity;
import racingcar.repository.entity.UsersEntity;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
class SaveRacingCarResultServiceTest {

    private static final int FIRST_GAME_ID = 1;

    private final SaveRacingCarResultService saveRacingCarResultService;
    private final UsersDao usersDao;
    private final GameDao gameDao;
    private final GameUsersPositionDao gameUsersPositionDao;
    private final GameWinUsersDao gameWinUsersDao;

    @Autowired
    public SaveRacingCarResultServiceTest(final SaveRacingCarResultService saveRacingCarResultService,
                                          final UsersDao usersDao,
                                          final GameDao gameDao,
                                          final GameUsersPositionDao gameUsersPositionDao,
                                          final GameWinUsersDao gameWinUsersDao) {
        this.saveRacingCarResultService = saveRacingCarResultService;
        this.usersDao = usersDao;
        this.gameDao = gameDao;
        this.gameUsersPositionDao = gameUsersPositionDao;
        this.gameWinUsersDao = gameWinUsersDao;
    }

    @Test
    void RacingCarResult를_이용해_정보들을_DB에_저장한다() {
        final String modi = "modi";
        final String kokodak = "koko";
        final int trialCount = 10;
        final int modiPosition = 7;
        final int kokodakPosition = 5;

        final List<String> winners = List.of(modi);
        final List<Car> cars = List.of(
                Car.of(modi, 7),
                Car.of(kokodak, 5)
        );
        final RacingCarResult racingCarResult = new RacingCarResult(winners, cars, trialCount);

        saveRacingCarResultService.saveRacingCarResult(racingCarResult);

        assertUsers(modi, kokodak);
        assertGame(trialCount);
        assertGameUsersPosition(modiPosition, kokodakPosition);
        assertGameWinUsers(modi);
    }

    private void assertUsers(final String modi, final String kokodak) {
        final UsersEntity modiEntity = usersDao.findByName(modi);
        final UsersEntity kokodakEntity = usersDao.findByName(kokodak);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(modiEntity.getName()).isEqualTo(modi);
        softAssertions.assertThat(kokodakEntity.getName()).isEqualTo(kokodak);
        softAssertions.assertAll();
    }

    private void assertGame(final int trialCount) {
        final List<GameEntity> games = gameDao.findAll();
        final GameEntity gameEntity = games.get(0);

        assertThat(gameEntity.getTrialCount()).isEqualTo(trialCount);
    }

    private void assertGameUsersPosition(final int modiPosition, final int kokodakPosition) {
        final List<GameUsersPositionEntity> gameUsersPositionEntities = gameUsersPositionDao.findByGameId(FIRST_GAME_ID);

        final Set<Integer> positions = gameUsersPositionEntities.stream()
                .map(GameUsersPositionEntity::getPosition)
                .collect(Collectors.toSet());

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(positions.contains(modiPosition)).isTrue();
        softAssertions.assertThat(positions.contains(kokodakPosition)).isTrue();
        softAssertions.assertAll();
    }

    private void assertGameWinUsers(final String modi) {
        final List<GameWinUsersEntity> gameWinUsersEntities = gameWinUsersDao.findByGameId(FIRST_GAME_ID);
        final GameWinUsersEntity winnerEntity = gameWinUsersEntities.get(0);

        final long modiUserId = winnerEntity.getUsersId();
        final UsersEntity modiEntity = usersDao.findById(modiUserId);

        assertThat(modiEntity.getName()).isEqualTo(modi);
    }
}
