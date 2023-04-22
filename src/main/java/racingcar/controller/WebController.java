package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.ServiceControllerDto;
import racingcar.service.WebService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebController {
    private final WebService webService;

    public WebController(final WebService webService) {
        this.webService = webService;
    }

    @PostMapping("/plays")
    public PlayResponseDto postInput(@RequestBody PlayRequestDto playRequestDto) {
        ServiceControllerDto serviceControllerDto = webService.play(playRequestDto.getCount(), playRequestDto.getNames());
        return new PlayResponseDto(serviceControllerDto.getWinners(), serviceControllerDto.getGameLog());
    }

    @GetMapping("/plays")
    public List<PlayResponseDto> getWinners() {
        return getGameLogResponseDtos();
    }

    private List<PlayResponseDto> getGameLogResponseDtos() {
        return webService.mappingEachGame()
                .stream()
                .map(dto -> new PlayResponseDto(dto.getWinners(), dto.getGameLog()))
                .collect(Collectors.toList());
    }
}
