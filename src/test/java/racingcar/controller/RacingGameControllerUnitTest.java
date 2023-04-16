package racingcar.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultResponseDto;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest(RacingGameController.class)
class RacingGameControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RacingGameService racingGameService;
    private GameInputDto gameInputDto;
    
    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        gameInputDto = new GameInputDto("아벨,스플릿,포비", "12");
    }
    
    @Test
    void 이름과_시도횟수를_전달하면_게임_결과를_반환한다() {
        List<Car> winners = List.of(new Car("아벨", 0));
        List<Car> cars = List.of(new Car("아벨", 0), new Car("스플릿", 0), new Car("포비", 0));
        RacingResultResponseDto racingResultResponseDto = new RacingResultResponseDto(winners, cars);
        
        given(racingGameService.playGameWithoutPrint(any(), any())).willReturn(racingResultResponseDto);
        
        RestAssuredMockMvc.given().log().all()
                .contentType(ContentType.JSON)
                .body(gameInputDto)
                .when().post("/plays")
                .then().log().all()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("winners", is("아벨"))
                .body("racingCars.name", contains("아벨", "스플릿", "포비"));
    }
    
    @Test
    void 진행했던_모든_게임의_결과를_반환한다() {
        List<Car> firstWinners = List.of(new Car("아벨", 0));
        List<Car> secondWinners = List.of(new Car("스플릿", 0));
        List<Car> firstCars = List.of(new Car("아벨", 0), new Car("스플릿", 0), new Car("포비", 0));
        List<Car> secondCars = List.of(new Car("아벨", 0), new Car("스플릿", 0));
        RacingResultResponseDto firstResult = new RacingResultResponseDto(firstWinners, firstCars);
        RacingResultResponseDto secondResult = new RacingResultResponseDto(secondWinners, secondCars);
        List<RacingResultResponseDto> racingResultResponseDtos = List.of(firstResult, secondResult);
        
        given(racingGameService.findAllGameResult()).willReturn(racingResultResponseDtos);
        
        RestAssuredMockMvc.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("size()", is(2))
                .body("[0].winners", is("아벨"))
                .body("[1].winners", is("스플릿"))
                .body("[0].racingCars.name", contains("아벨", "스플릿", "포비"))
                .body("[1].racingCars.name", contains("아벨", "스플릿"));
    }
}