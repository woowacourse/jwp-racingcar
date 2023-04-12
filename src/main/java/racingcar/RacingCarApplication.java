package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RacingCarApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(final String[] args) {
        SpringApplication.run(RacingCarApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE racing_game IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE racing_game (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, trial_count INT, winners VARCHAR(255), play_time DATETIME DEFAULT CURRENT_TIMESTAMP)");


        jdbcTemplate.execute("DROP TABLE racing_car IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE racing_car (" +
                "id INT AUTO_INCREMENT PRIMARY_KEY, name VARCHAR(5), position INT, racing_game_id INT, FOREIGN KEY (racing_game_id) REFERENCES racing_game(id) ON DELETE CASCADE)");
    }
}
