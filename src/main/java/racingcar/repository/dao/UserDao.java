package racingcar.repository.dao;

import racingcar.repository.entity.UserEntity;

public interface UserDao {

    long save(final UserEntity userEntity);

    UserEntity findById(final long id);

    UserEntity findByName(final String name);
}
