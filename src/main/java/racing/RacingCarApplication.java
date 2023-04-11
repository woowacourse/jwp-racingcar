package racing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RacingCarApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RacingCarApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS cars (" +
                "car_id bigint NOT NULL AUTO_INCREMENT, " +
                "car_name varchar(8) NOT NULL, " +
                "step int(3) NOT NULL, " +
                "winner boolean default false, " +
                "PRIMARY KEY (car_id)" +
                ");"
        );

        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS games (" +
                        "game_id bigint NOT NULL AUTO_INCREMENT, " +
                        "count int(3) NOT NULL, " +
                        "create_time timestamp default current_timestamp NOT NULL, " +
                        "PRIMARY KEY (game_id)" +
                        ");"
        );
    }
}
