package racingcar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.RecordEntity;
import racingcar.repository.RecordDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RecordServiceTest {

    @Mock
    private RecordDao recordDao;
    @InjectMocks
    private RecordService recordService;


    @Test
    @DisplayName("기록 저장 테스트")
    void 기록_저장_테스트() {
        Assertions.assertDoesNotThrow(() -> recordService.save(10, new Cars(List.of(new Car("a"), new Car("b")))));
    }

    @Test
    @DisplayName("모든 기록 출력 테스트")
    void 모든_기록_출력_테스트() {
        given(recordDao.findAll()).willReturn(List.of(
                new RecordEntity("a", 1, 5, false),
                new RecordEntity("b", 1, 5, true)
        ));

        assertThat(recordService.findAll().size()).isEqualTo(2);
    }
}
