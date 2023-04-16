package racingcar.dao;

import org.springframework.jdbc.core.RowMapper;
import racingcar.dto.CarDTO;

public class ObjectMapper {

    public static RowMapper<CarDTO> carDTOMapper = (resultSet, rowNum) -> {
        return new CarDTO(
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
    };
}
