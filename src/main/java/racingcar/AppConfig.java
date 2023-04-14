package racingcar;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    DataSource dataSource() {
        return DataSourceBuilder.create()
                                .url("jdbc:h2:mem:testdb")
                                .username("sa")
                                .password("")
                                .type(HikariDataSource.class)
                                .build();
    }
}
