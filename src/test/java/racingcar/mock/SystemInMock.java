package racingcar.mock;

import java.io.InputStream;

public class SystemInMock extends InputStream {
    private boolean returnNull;
    
    public SystemInMock(final boolean returnNull) {
        this.returnNull = returnNull;
    }
    
    @Override
    public int read() {
        if (returnNull) {
            return -1;
        } else {
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}
