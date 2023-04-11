package racingcar.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.PlayResultEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarMapperTest {

    @Autowired
    private PlayResultMapper playResultMapper;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE car IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE CAR" +
                "(" +
                "    id             INT         NOT NULL AUTO_INCREMENT," +
                "    play_result_id INT         NOT NULL," +
                "    name           VARCHAR(50) NOT NULL," +
                "    position       INT         NOT NULL," +
                "    PRIMARY KEY (id)" +
                ");");
    }

    @Test
    void key() {
        PlayResultEntity playResultEntity = new PlayResultEntity(0, "aa", null);
        Long playResultId = playResultMapper.save(playResultEntity);
        CarEntity carEntity = new CarEntity(0, playResultId, "juno", 3);
        Long carId = carMapper.save(carEntity);
        CarEntity result = carMapper.findById(carId);
        System.out.println(result);
        assertThat(result).isNotNull();
    }
}
