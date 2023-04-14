package racingcar.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingResponse;
import racingcar.service.RacingcarService;

@RestController
public class RacingcarController {

    private final RacingcarService racingcarService;

    public RacingcarController(RacingcarService racingcarService) {
        this.racingcarService = racingcarService;
    }

    @PostMapping("/plays")
    public RacingResponse plays(@RequestBody RacingGameRequest request) {
        List<String> carNames = InputConvertor.carNames(request.getNames());
        int tryCount = InputConvertor.tryCount(request.getCount());
        return racingcarService.move(carNames, tryCount);
    }

    @GetMapping("/plays")
    public List<RacingResponse> allResults(){
        return racingcarService.allResults();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handler(IllegalArgumentException exception){
        return new ErrorMessage(exception.getMessage());
    }

}
