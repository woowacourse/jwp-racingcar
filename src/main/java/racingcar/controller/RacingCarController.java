package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import racingcar.domain.GameManager;
import racingcar.domain.NumberGenerator;
import racingcar.dto.*;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

@Controller
public class RacingCarController {

    public ResponseEntity<ResultResponse> play(NamesAndCountRequest request) {

        return null;
    }
}
