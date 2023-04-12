package racingcar.repository.dao;

import racingcar.repository.entity.UsersEntity;

public interface UsersDao {

    long save(final UsersEntity usersEntity);

    UsersEntity findById(final long id);

    UsersEntity findByName(final String name);
}
