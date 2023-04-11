package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.domain.Car;
import racingcar.domain.RandomMoveChance;
import racingcar.domain.SpringService;
import racingcar.dto.GameInputDto;

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
    public ResponseEntity postInput(@RequestBody GameInputDto gameInputDto){
        Long id = 1L;
        System.out.println(gameInputDto);
        SpringService springService = new SpringService(gameInputDto,new RandomMoveChance());
        springService.play();
        return ResponseEntity.created(URI.create(""+id)).build();
    }
}
