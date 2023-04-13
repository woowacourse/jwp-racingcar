package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.UpdatingDAO;
import racingcar.dto.*;
import racingcar.utils.RacingUtil;
import racingcar.vo.Trial;

@RestController
public class WebController {
    final private UpdatingDAO updatingDAO;

    public WebController(UpdatingDAO updatingDAO){
        this.updatingDAO = updatingDAO;
    }

    @PostMapping("/plays")
    public FinalResultDto playRacing(@RequestBody RequestBodyDTO dto) {
        ResultDto resultDto = RacingUtil.race(dto.getNames(), dto.getCount());
        final int racingId = updatingDAO.insert(Trial.of(dto.getCount()));
        for(CarDto car : resultDto.getRacingCars()){
            String name = car.getName();
            updatingDAO.insert(new CarInfoDto(racingId, name, car.getPosition(), resultDto.isWinnerContaining(name)));
        }
        return new FinalResultDto(resultDto);
    }
}
