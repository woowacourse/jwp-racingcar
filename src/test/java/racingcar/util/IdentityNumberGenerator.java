package racingcar.util;

import java.util.List;

public class IdentityNumberGenerator implements NumberGenerator{

    private final List<Integer> identity;
    private int index;

    public IdentityNumberGenerator(List<Integer> identity) {
        this.identity = identity;
        index = -1;
    }

    @Override
    public int generate() {
        index = index + 1;
        if (index > identity.size() - 1) {
            throw new ArrayIndexOutOfBoundsException("숫자 생성 범위를 초과했습니다.");
        }
        return identity.get(index);
    }
}
