package racingcar.view;

import racingcar.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String BAR = "-";
    private static final String DIVIDING_LINE = "=============";
    
    public void printGameResult(final List<GameResponseDto> gameResponseDtos) {
        System.out.println(parseGameResultsDisplay(gameResponseDtos));
    }
    
    private String parseGameResultsDisplay(final List<GameResponseDto> gameResponseDtos) {
        return gameResponseDtos.stream()
                .map(this::parseGameResultDisplay)
                .collect(Collectors.joining(NEW_LINE + NEW_LINE + DIVIDING_LINE + NEW_LINE + NEW_LINE, NEW_LINE, ""));
    }
    
    private String parseGameResultDisplay(final GameResponseDto gameResponseDto) {
        final StringBuilder stringBuilder = new StringBuilder();
        saveCarsGameResultDisplay(gameResponseDto, stringBuilder);
        stringBuilder.append("우승자 : ").append(gameResponseDto.getWinners());
        
        return stringBuilder.toString();
    }
    
    private void saveCarsGameResultDisplay(final GameResponseDto gameResponseDto, final StringBuilder stringBuilder) {
        final String carsGameResultDisplay = gameResponseDto.getRacingCars().stream()
                .map(this::parseCarDisplay)
                .collect(Collectors.joining(NEW_LINE));
        stringBuilder.append("최종 현황)").append(NEW_LINE).append(carsGameResultDisplay).append(NEW_LINE).append(NEW_LINE);
    }
    
    private String parseCarDisplay(final CarResponseDto carResponseDto) {
        return carResponseDto.getName() + " : " + BAR.repeat(carResponseDto.getPosition());
    }
}
