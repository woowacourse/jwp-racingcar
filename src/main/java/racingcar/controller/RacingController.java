package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.RacingDao;
import racingcar.dto.*;
import racingcar.service.RacingService;
import racingcar.vo.Trial;

@RestController
public class RacingController {
    private final RacingDao racingDao;
    private final RacingService racingService;

    public RacingController(RacingDao racingDao, RacingService racingService) {
        this.racingDao = racingDao;
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public FinalResultDto playRacing(@RequestBody RequestBodyDTO dto) {
        ResultDto resultDto = racingService.race(dto.getNames(), dto.getCount());
        final int racingId = racingDao.insert(Trial.of(dto.getCount()));
        for (CarDto car : resultDto.getRacingCars()) {
            String name = car.getName();
            racingDao.insert(new CarInfo(racingId, name, car.getPosition(), resultDto.isWinnerContaining(name)));
        }
        return new FinalResultDto(resultDto);
    }
}
