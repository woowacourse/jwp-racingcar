package racingcar.mock;

import java.io.IOException;
import java.io.InputStream;

public class SystemInMock extends InputStream {
    private boolean returnNull;
    
    public SystemInMock(boolean returnNull) {
        this.returnNull = returnNull;
    }
    
    @Override
    public int read() {
        if (returnNull) {
            return -1;
        } else {
            // 여기에서 필요한 경우 다른 동작을 구현할 수 있습니다.
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}
