package racingcar.repository.dao;

import racingcar.repository.entity.UserEntity;

public class ConsoleUserDao implements UserDao {

    @Override
    public long save(final UserEntity userEntity) {
        return 0;
    }

    @Override
    public UserEntity findByName(final String name) {
        return new UserEntity("");
    }
}
