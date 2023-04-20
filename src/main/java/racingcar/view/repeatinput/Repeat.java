package racingcar.view.repeatinput;

import java.util.function.Supplier;

public class Repeat {
    public static <T> T repeatSupplierAtException(Supplier<T> inputProcess) {
        try {
            return inputProcess.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("[ERROR] " + illegalArgumentException);
            return repeatSupplierAtException(inputProcess);
        }
    }
    
    public static void repeatJustRunnableAtException(JustRunnable inputProcess) {
        try {
            inputProcess.run();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("[ERROR] " + illegalArgumentException);
            repeatJustRunnableAtException(inputProcess);
        }
    }
}
