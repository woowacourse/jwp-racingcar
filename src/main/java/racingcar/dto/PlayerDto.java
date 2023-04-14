package racingcar.dto;

public class PlayerDto {
    private final Long id;
    private final String name;

    public PlayerDto(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
