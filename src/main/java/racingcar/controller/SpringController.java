package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.domain.Car;
import racingcar.domain.RandomMoveChance;
import racingcar.domain.SpringService;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;

import java.net.URI;

@RestController
@RequestMapping("/plays")
public class SpringController {
    @PostMapping("/car")
    public ResponseEntity createCar(@RequestBody Car car){
        Long id = 1L;
        return ResponseEntity.created(URI.create("/car/"+id)).build();
    }

    @PostMapping("")
    public ResponseEntity postInput(@RequestBody RequestDto requestDto){
        Long id = 1L;
        System.out.println(requestDto);
        SpringService springService = new SpringService(requestDto,new RandomMoveChance());
        springService.play();
        ResponseDto responseDto = new ResponseDto(springService.findWinners(),springService.getCars());
        return ResponseEntity.ok()
                .body(responseDto);
    }
}
