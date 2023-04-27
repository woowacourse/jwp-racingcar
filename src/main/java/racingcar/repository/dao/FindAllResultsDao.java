package racingcar.repository.dao;

import java.util.List;

import racingcar.repository.dto.ResultDto;

public interface FindAllResultsDao {

    List<ResultDto> findAll();
}
