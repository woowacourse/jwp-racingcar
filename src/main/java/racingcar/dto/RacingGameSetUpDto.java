package racingcar.dto;

import racingcar.dto.view.PlayRequest;
import racingcar.services.RacingGameService;
import racingcar.util.ValueEditor;

import java.util.List;

public class RacingGameSetUpDto {

    private final List<String> names;
    private final String count;

    public RacingGameSetUpDto(List<String> names, String count) {
        this.names = names;
        this.count = count;
    }

    public static RacingGameSetUpDto from(PlayRequest playRequest){
        List<String> names = ValueEditor.splitByComma(playRequest.getNames());
        return new RacingGameSetUpDto(names, playRequest.getCount());
    }

    public List<String> getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
