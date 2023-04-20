package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.GameDto;
import racingcar.dto.GameResultDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String BAR = "-";
    private static final String DIVIDING_LINE = "=============";
    
    public void printGameResult(final List<GameResultDto> gameResultDtos) {
        System.out.println(parseGameResultsDisplay(gameResultDtos));
    }
    
    private String parseGameResultsDisplay(final List<GameResultDto> gameResultDtos) {
        return gameResultDtos.stream()
                .map(this::parseGameResultDisplay)
                .collect(Collectors.joining(NEW_LINE + NEW_LINE + DIVIDING_LINE + NEW_LINE + NEW_LINE));
    }
    
    private String parseGameResultDisplay(final GameResultDto gameResultDto) {
        final StringBuilder stringBuilder = new StringBuilder();
        saveGameDisplay(gameResultDto, stringBuilder);
        saveCarsGameResultDisplay(gameResultDto, stringBuilder);
        saveWinnersNameDisplay(gameResultDto, stringBuilder);
        
        return stringBuilder.toString();
    }
    
    private void saveGameDisplay(final GameResultDto gameResultDto, final StringBuilder stringBuilder) {
        final GameDto gameDto = gameResultDto.getGameDto();
        final String date = gameDto.getDate();
        final String time = gameDto.getTime();
        
        stringBuilder.append(NEW_LINE)
                .append("날짜 및 시간 : ").append(date).append(" ").append(time).append(NEW_LINE)
                .append("시도 횟수 : ").append(gameDto.getTryNumber()).append("회").append(NEW_LINE).append(NEW_LINE);
    }
    
    private void saveCarsGameResultDisplay(final GameResultDto gameResultDto, final StringBuilder stringBuilder) {
        final String carsGameResultDisplay = gameResultDto.getCarDtos().stream()
                .map(this::parseCarDisplay)
                .collect(Collectors.joining(NEW_LINE));
        stringBuilder.append("최종 현황)").append(NEW_LINE).append(carsGameResultDisplay).append(NEW_LINE).append(NEW_LINE);
    }
    
    private String parseCarDisplay(final CarDto carDto) {
        return carDto.getName() + " : " + BAR.repeat(carDto.getPosition());
    }
    
    private void saveWinnersNameDisplay(final GameResultDto gameResultDto, final StringBuilder stringBuilder) {
        final String winnersName = gameResultDto.getWinnerCarDtos().stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(", "));
        stringBuilder.append("우승자 : ").append(winnersName);
    }
}
