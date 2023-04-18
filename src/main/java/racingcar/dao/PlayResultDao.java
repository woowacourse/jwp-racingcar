package racingcar.dao;

import java.util.List;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public interface PlayResultDao {

    int insertPlayResult(final PlayResult playResult);

    List<PlayResult> selectAllResults();
}
