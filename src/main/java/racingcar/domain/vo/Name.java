package racingcar.domain.vo;

import java.util.Objects;

public class Name {

	private static final int NAME_MIN_LENGTH = 1;
	private static final int NAME_MAX_LENGTH = 5;

	private final String name;

	public Name(final String input) {
		final String name = input.strip();
		validateName(name);
		this.name = name;
	}

	private void validateName(final String name) {
		validateBlank(name);
		validateLength(name);
	}

	private void validateBlank(final String name) {
		if (name.isBlank()) {
			throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
		}
	}

	private void validateLength(final String name) {
		if (isOutOfRange(name.length())) {
			throw new IllegalArgumentException("1 ~ 5글자 사이의 이름을 입력해주세요.");
		}
	}

	private boolean isOutOfRange(final int length) {
		return length < NAME_MIN_LENGTH || NAME_MAX_LENGTH < length;
	}

	public String getValue() {
		return name;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Name name1 = (Name)o;
		return Objects.equals(name, name1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
