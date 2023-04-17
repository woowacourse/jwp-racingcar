package racingcar.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultResponseDto;
import racingcar.service.RacingGameServiceImpl;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest(WebRacingGameController.class)
class WebRacingGameControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RacingGameServiceImpl racingGameServiceImpl;
    
    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
    
    @Test
    void 이름과_시도횟수를_전달하면_게임_결과를_반환한다() {
        GameInputDto gameInputDto = new GameInputDto("아벨,스플릿,포비", "12");
        List<Car> winners = List.of(new Car("아벨", 0));
        List<Car> cars = List.of(new Car("아벨", 0), new Car("스플릿", 0), new Car("포비", 0));
        RacingResultResponseDto racingResultResponseDto = new RacingResultResponseDto(winners, cars);
        
        given(racingGameServiceImpl.playGameWithoutPrint(any(), any())).willReturn(racingResultResponseDto);
        
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
        
        given(racingGameServiceImpl.findAllGameResult()).willReturn(racingResultResponseDtos);
        
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
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void names_null_또는_empty_입력_시_예외_발생(String names) {
        RestAssuredMockMvc.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameInputDto(names, "12"))
                .when().post("/plays")
                .then().log().all()
                .status(HttpStatus.BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("message", is("이름을 입력해주세요"));
    }
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void count_null_또는_empty_입력_시_예외_발생(String count) {
        RestAssuredMockMvc.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameInputDto("abel", count))
                .when().post("/plays")
                .then().log().all()
                .status(HttpStatus.BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("message", is("시도 횟수를 입력해주세요"));
    }
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void names_and_count_null_또는_empty_입력_시_예외_발생(String param) {
        RestAssuredMockMvc.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameInputDto(param, param))
                .when().post("/plays")
                .then().log().all()
                .status(HttpStatus.BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("message", containsString("이름을 입력해주세요"))
                .body("message", containsString(System.lineSeparator()))
                .body("message", containsString("시도 횟수를 입력해주세요"));
    }
}