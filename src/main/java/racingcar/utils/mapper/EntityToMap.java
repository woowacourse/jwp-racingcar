package racingcar.utils.mapper;

import java.util.Map;

public class EntityToMap {
    public static <T> Map<String, Object> convert(EntityMapper<T, Map<String, Object>> entityMapper, T arg) {
        return entityMapper.convert(arg);
    }

    public static <T> Map<String, Object> convert(EntityMapper<T, Map<String, Object>> entityMapper, T arg, int resultId) {
        Map<String, Object> param = entityMapper.convert(arg);
        param.put("result_id", resultId);
        return param;
    }
}
