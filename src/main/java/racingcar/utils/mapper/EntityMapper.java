package racingcar.utils.mapper;

@FunctionalInterface
public interface EntityMapper<T, R> {
    R convert(T arg);
}
