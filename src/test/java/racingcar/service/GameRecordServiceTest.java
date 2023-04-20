package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.model.RecordEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GameRecordServiceTest {

    @Mock
    private RecordService recordService;

    @InjectMocks
    private GameRecordService gameRecordService;

    @Test
    @DisplayName("모든 기록 출력 테스트")
    void 모든_기록_출력_테스트() {
        given(recordService.findAll()).willReturn(List.of(
                new RecordEntity("a", 1, 5, false),
                new RecordEntity("b", 1, 6, true),
                new RecordEntity("a", 2, 5, false),
                new RecordEntity("b", 2, 7, true)
        ));

        assertThat(gameRecordService.findRecords()).hasSize(2);
    }
}
