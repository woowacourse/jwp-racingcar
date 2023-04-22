package racingcar.common;

import java.util.function.Supplier;

public class ExecuteContext {

    public static <T> T repeatableExecute(final Supplier<T> executeStrategy) {
        T result = null;
        while (result == null) {
            try {
                result = executeStrategy.get();
            } catch (final IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return result;
    }
}
