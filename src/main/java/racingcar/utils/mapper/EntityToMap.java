package racingcar.utils.mapper;

import java.util.Map;

public class EntityToMap {
    public static <T> Map<String, Object> convert(EntityMapper<T, Map<String, Object>> entityMapper, T arg) {
        return entityMapper.convert(arg);
    }
}
