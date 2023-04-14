package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class DbInitializer {
    
    public static void init(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        jdbcTemplate.execute("DROP TABLE player IF EXISTS cascade constraints");
        jdbcTemplate.execute("DROP TABLE RACE IF EXISTS cascade constraints");
        jdbcTemplate.execute("DROP TABLE WINNER IF EXISTS cascade constraints");
        jdbcTemplate.execute("CREATE TABLE RACE (\n" +
                "    id          INT         NOT NULL AUTO_INCREMENT,\n" +
                "    play_count  INT         NOT NULL,\n" +
                "    created_at  DATETIME    NOT NULL default current_timestamp,\n" +
                "    PRIMARY KEY (id)\n" +
                ")");
        jdbcTemplate.execute("CREATE TABLE PLAYER (\n" +
                "    id          INT           NOT NULL AUTO_INCREMENT,\n" +
                "    name        VARCHAR(50)   NOT NULL,\n" +
                "    identifier  INT           NOT NULL,\n" +
                "    position    INT           NOT NULL,\n" +
                "    race_id     INT           NOT NULL,\n" +
                "    PRIMARY KEY (id),\n" +
                "    FOREIGN KEY (race_id) REFERENCES RACE(id)\n" +
                ")");
        jdbcTemplate.execute("CREATE TABLE WINNER (\n" +
                "    id          INT         NOT NULL AUTO_INCREMENT,\n" +
                "    race_id     INT         NOT NULL,\n" +
                "    player_id   INT         NOT NULL,\n" +
                "    PRIMARY KEY (id),\n" +
                "    FOREIGN KEY (race_id) REFERENCES RACE(id),\n" +
                "    FOREIGN KEY (player_id) REFERENCES PLAYER(id)\n" +
                ")");
    }
}
