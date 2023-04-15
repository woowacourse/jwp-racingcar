package racingcar;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class TableTruncator {

	public static void truncateTables(final JdbcTemplate jdbcTemplate) {
		List<String> truncateQueries = getTruncateQueries(jdbcTemplate);
		truncate(jdbcTemplate, truncateQueries);
	}

	private static List<String> getTruncateQueries(final JdbcTemplate jdbcTemplate) {
		return jdbcTemplate.queryForList("SELECT Concat('TRUNCATE TABLE ', TABLE_NAME, ';') AS q FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'", String.class);
	}

	private static void truncate(final JdbcTemplate jdbcTemplate, final List<String> truncateQueries) {
		execute(jdbcTemplate, "SET REFERENTIAL_INTEGRITY FALSE");
		truncateQueries.forEach(v -> execute(jdbcTemplate, v));
		execute(jdbcTemplate, "SET REFERENTIAL_INTEGRITY TRUE");
	}

	private static void execute(final JdbcTemplate jdbcTemplate, final String query) {
		jdbcTemplate.execute(query);
	}
}
