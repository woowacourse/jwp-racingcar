package racingcar.dto;

import racingcar.domain.Winner;
import racingcar.domain.Winners;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class WinnerDto {

    private final String name;

    private WinnerDto(final String name) {
        this.name = name;
    }

    public static WinnerDto from(final WinnerEntity winner) {
        return new WinnerDto(winner.getName());
    }

    public static List<WinnerDto> createWinnerDtos(final Winners winners) {
        return winners.getWinners().stream()
                .map(WinnerDto::from)
                .collect(Collectors.toList());
    }

    public static WinnerDto from(final Winner winner) {
        return new WinnerDto(winner.getName());
    }

    public String getName() {
        return name;
    }
}
