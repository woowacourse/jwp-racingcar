package racingcar.repository.dao;

import racingcar.repository.entity.UserEntity;

public interface UserDao {

    long save(final UserEntity userEntity);

    UserEntity findByName(final String name);
}
