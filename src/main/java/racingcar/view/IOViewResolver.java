package racingcar.view;

import racingcar.dto.input.CarNameRequest;
import racingcar.dto.input.TryCountRequest;
import racingcar.dto.output.PrintCriticalExceptionDto;
import racingcar.dto.output.PrintExceptionDto;
import racingcar.dto.output.PrintMovingStatusDto;
import racingcar.dto.output.PrintWinnersDto;
import racingcar.view.exception.NotFoundViewException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class IOViewResolver {

    private final Map<Class<?>, Supplier<Object>> inputViewMap;
    private final Map<Class<?>, Consumer<Object>> outputViewMap;

    public IOViewResolver(final InputView inputView, final OutputView outputView) {
        inputViewMap = new HashMap<>();
        outputViewMap = new HashMap<>();
        initInputViewMappings(inputView);
        initOutputViewMappings(outputView);
    }

    private void initInputViewMappings(final InputView inputView) {
        inputViewMap.put(CarNameRequest.class, inputView::readCarNames);
        inputViewMap.put(TryCountRequest.class, inputView::readTryCount);
    }

    private void initOutputViewMappings(final OutputView outputView) {
        outputViewMap.put(PrintMovingStatusDto.class, dto -> outputView.printTotalMovingStatus((PrintMovingStatusDto) dto));
        outputViewMap.put(PrintWinnersDto.class, dto -> outputView.printWinners((PrintWinnersDto) dto));
        outputViewMap.put(PrintExceptionDto.class, dto -> outputView.printException((PrintExceptionDto) dto));
        outputViewMap.put(PrintCriticalExceptionDto.class, dto -> outputView.printCriticalException((PrintCriticalExceptionDto) dto));
    }

    public <T> T inputViewResolve(final Class<T> type) {
        try {
            return type.cast(inputViewMap.get(type).get());
        } catch (NullPointerException e) {
            throw new NotFoundViewException();
        }
    }

    public void outputViewResolve(final Object dto) {
        try {
            outputViewMap.get(dto.getClass()).accept(dto);
        } catch (NullPointerException e) {
            throw new NotFoundViewException();
        }
    }
}
