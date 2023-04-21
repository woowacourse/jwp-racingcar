package racingcar.dto;

import java.util.List;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import racingcar.domain.CarName;
import racingcar.domain.Participants;
import racingcar.domain.Race;

public class PlayRequest {

    @Size(
            min = Participants.MIN_PARTICIPANT_SIZE,
            max = Participants.MAX_PARTICIPANT_SIZE,
            message = Participants.PARTICIPANT_SIZE_ERROR_MESSAGE)
    private final List<@Size(
            min = CarName.MIN_CAR_NAME_LENGTH,
            max = CarName.MAX_CAR_NAME_LENGTH,
            message = CarName.CAR_NAME_LENGTH_ERROR_MESSAGE) String> names;

    @Range(min = Race.MIN_TRIAL_COUNT, max = Race.MAX_TRIAL_COUNT, message = Race.TRIAL_COUNT_ERROR_MESSAGE)
    private final int count;

    public PlayRequest(List<String> names, int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
