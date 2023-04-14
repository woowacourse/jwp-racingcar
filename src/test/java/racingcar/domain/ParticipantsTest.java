package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantsTest {
    @Test
    @DisplayName("Participants 생성")
    void create() {
        Participants participants = new Participants(List.of("Abel", "Split"));
        assertThat(participants.getCars()).containsExactly(new Car("Abel", 0), new Car("Split", 0));
    }
}