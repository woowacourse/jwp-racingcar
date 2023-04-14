package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.RacingDao;
import racingcar.dto.*;
import racingcar.utils.RacingUtil;
import racingcar.vo.Trial;

@RestController
public class RacingController {
    private final RacingDao racingDao;
    private final RacingUtil racingUtil;

    public RacingController(RacingDao racingDao, RacingUtil racingUtil) {
        this.racingDao = racingDao;
        this.racingUtil = racingUtil;
    }

    @PostMapping("/plays")
    public FinalResultDto playRacing(@RequestBody RequestBodyDTO dto) {
        ResultDto resultDto = racingUtil.race(dto.getNames(), dto.getCount());
        final int racingId = racingDao.insert(Trial.of(dto.getCount()));
        for (CarDto car : resultDto.getRacingCars()) {
            String name = car.getName();
            racingDao.insert(new CarInfo(racingId, name, car.getPosition(), resultDto.isWinnerContaining(name)));
        }
        return new FinalResultDto(resultDto);
    }
}
