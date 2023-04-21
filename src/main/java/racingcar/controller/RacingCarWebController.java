package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.database.RacingCarDao;
import racingcar.database.RacingGameDao;
import racingcar.model.RacingCarRequest;
import racingcar.model.RacingCarResponse;
import racingcar.model.RacingCarResult;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarWebController {
    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;
    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;

    @Autowired
    public RacingCarWebController(final RacingCarService racingCarService, final RacingGameDao racingGameDao, final RacingCarDao racingCarDao) {
        this.racingCarService = racingCarService;
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<RacingCarResult> play(@RequestBody final RacingCarRequest request) {
        final List<String> names = Arrays.stream(request.getNames().split(DELIMITER))
                .collect(Collectors.toList());
        final int count = request.getCount();

        final RacingCarResult racingCarResult = racingCarService.playRacingCar(names, count);
        saveResult(count, racingCarResult);

        return ResponseEntity.ok().body(racingCarResult);
    }

    private void saveResult(final int count, final RacingCarResult racingCarResult) {
        final int gameId = racingGameDao.insert(count);

        racingCarResult.getRacingCars().forEach(car ->
                racingCarDao.insert(car, gameId, racingCarResult.isWinner(car)));
    }

    @GetMapping(path = "/plays")
    public List<RacingCarResponse> getLog() {
        return getRacingCarLog();
    }

    private List<RacingCarResponse> getRacingCarLog() {
        final List<Integer> gameIds = racingGameDao.selectGameIds();

        return gameIds.stream()
                .map(this::generateLog)
                .collect(Collectors.toList());
    }

    private RacingCarResponse generateLog(final int gameId) {
        final RacingCarResult result = new RacingCarResult(racingCarDao.selectWinners(gameId), racingCarDao.selectCarsBy(gameId));
        return new RacingCarResponse(result);
    }
}
