package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Names;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingCarResponse;

@Controller
public class RacingGameController {

    private final RacingCarDao racingCarDao;

    @Autowired
    public RacingGameController(final RacingCarDao racingCarDao) {
        this.racingCarDao = racingCarDao;
    }

    @Transactional
    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacingCarResponse> play(@RequestBody final RacingCarRequest racingCarRequest) {
        RacingCars racingCars = createRacingCar(racingCarRequest);
        TryCount tryCount = new TryCount(racingCarRequest.getTryCount());

        playGame(racingCars, tryCount);

        racingCarDao.insertGame(racingCars, tryCount);

        final List<RacingCarDto> racingCarDtos = createRacingCarDtos(racingCars);

        return ResponseEntity.ok().body(
                new RacingCarResponse(racingCars.getWinnerNames(), racingCarDtos));
    }

    private List<RacingCarDto> createRacingCarDtos(final RacingCars racingCars) {
        return racingCars.getRacingCars()
                .stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private RacingCars createRacingCar(RacingCarRequest racingCarRequest) {
        Names names = new Names(racingCarRequest.getNames());
        return new RacingCars(createRacingCar(names));
    }

    private List<RacingCar> createRacingCar(Names names) {
        return names.getNames()
                .stream()
                .map(RacingCar::new)
                .collect(Collectors.toList());
    }

    private void playGame(RacingCars racingCars, TryCount tryCount) {
        while (canProceed(tryCount)) {
            racingCars.moveAll();
            tryCount = tryCount.deduct();
        }
    }

    private boolean canProceed(TryCount tryCount) {
        return tryCount.isOpportunity();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
