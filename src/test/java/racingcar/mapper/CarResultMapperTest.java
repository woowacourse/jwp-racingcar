package racingcar.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarResultEntity;
import racingcar.entity.PlayResultEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarResultMapperTest {

    private final PlayResultMapper playResultMapper;
    private final CarResultMapper carResultMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    CarResultMapperTest(PlayResultMapper playResultMapper, CarResultMapper carResultMapper, JdbcTemplate jdbcTemplate) {
        this.playResultMapper = playResultMapper;
        this.carResultMapper = carResultMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE car_result IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE CAR_RESULT" +
                "(" +
                "    id             INT         NOT NULL AUTO_INCREMENT," +
                "    play_result_id INT         NOT NULL," +
                "    name           VARCHAR(50) NOT NULL," +
                "    position       INT         NOT NULL," +
                "    PRIMARY KEY (id)" +
                ");");
    }

    @Test
    void 자동차_기록_저장_조회_테스트() {
        PlayResultEntity playResultEntity = PlayResultEntity.of(0, 10, "juno", null);
        long playResultId = playResultMapper.save(playResultEntity);
        CarResultEntity carResultEntity = CarResultEntity.of(0, playResultId, "juno", 3);
        long carId = carResultMapper.save(carResultEntity);
        CarResultEntity result = carResultMapper.findById(carId);
        System.out.println(result);
        assertThat(result).isNotNull();
    }
}
