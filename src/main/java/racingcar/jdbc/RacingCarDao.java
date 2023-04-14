package racingcar.jdbc;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.zaxxer.hikari.HikariDataSource;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;
import racingcar.dto.WinnerAndGameIdDto;

public class RacingCarDao {

	private final JdbcTemplate jdbcTemplate;

	public RacingCarDao() {
		final DataSource dataSource = DataSourceBuilder.create()
			.url("jdbc:h2:mem:testdb")
			.username("sa")
			.password("")
			.type(HikariDataSource.class)
			.build();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}

	private final RowMapper<WinnerAndGameIdDto> gameRowMapper = (resultSet, rowNum) -> new WinnerAndGameIdDto(
		resultSet.getInt("gameId"),
		resultSet.getString("winner")
	);

	private final RowMapper<CarDto> carRowMapper = (resultSet, rowNum) -> new CarDto(
			resultSet.getString("name"),
			resultSet.getInt("position")
		);

	public void insertCar(Cars cars, int count) {
		String sqlGame = "INSERT INTO games(count, winner, timestamp) VALUES (?,?,?)";
		String sqlCars = "INSERT INTO cars(name, position, gameId) VALUES(?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlGame, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, count);
			ps.setString(2, cars.winners());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			return ps;
		}, keyHolder);
		for (Car car : cars.getCars()) {
			jdbcTemplate.update(sqlCars, car.getName(), car.getPosition(), keyHolder.getKey().intValue());
		}
	}

	public List<ResultDto> find() {
		List<ResultDto> resultDtos = new ArrayList<>();

		String sqlGames = "SELECT winner, gameId FROM GAMES";
		List<WinnerAndGameIdDto> winnerAndGameIdDtos = jdbcTemplate.query(sqlGames, gameRowMapper);
		for (WinnerAndGameIdDto winnerAndGameIdDto : winnerAndGameIdDtos) {
			ResultDto resultDto = new ResultDto();
			String winner = winnerAndGameIdDto.getWinner();
			resultDto.setWinners(winner);

			String sqlCars = "SELECT name, position FROM cars WHERE gameId = ?";// ? <= gameId
			int gameId = winnerAndGameIdDto.getGameId();
			List<CarDto> carDtos = jdbcTemplate.query(sqlCars, carRowMapper, gameId);

			resultDto.setRacingCars(carDtos);
			resultDtos.add(resultDto);
		}
		return resultDtos;
	}
}
