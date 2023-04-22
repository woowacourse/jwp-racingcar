package racingcar.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfig {

	public static DataSource createDateSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL;AUTOCOMMIT=TRUE");
		// DB_CLOSE_DELAY=-1 : 마지막 연결이 닫히더라도 인메모리 데이터베이스를 계속 열어두도록 H2에 지시
		// AUTOCOMMIT = TRUE : 각 SQL 문 후에 트랜잭션을 커밋하도록 H2에 지시 (CAR 테이블이 생성될 때 GAME 테이블을 커밋하고 사용)

		dataSource.setUsername("sa");
		dataSource.setPassword("");

		return dataSource;
	}
}
