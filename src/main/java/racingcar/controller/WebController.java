package racingcar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.dto.RequestDto;
import racingcar.model.Cars;

import java.util.Arrays;
import java.util.List;

@Controller
public class WebController {

    @PostMapping("/plays")
    @ResponseBody
    public void playGame(@RequestBody RequestDto requestDto) {
        // TODO: 2023/04/11 이름 중복 검사
        List<String> carNames = Arrays.asList(requestDto.getNames().split(","));
        Cars cars = new Cars(carNames);
    }
}
