package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RaceTest {
    private Race race;
    
    @BeforeEach
    void setUp() {
        race = new Race("3");
    }
    
    @Test
    @DisplayName("카운트 증가 후 끝났는지 확인한다.")
    void isFinished() {
        assertAll(
                () -> race.addCount(),
                () -> assertThat(race.isFinished()).isFalse(),
                () -> race.addCount(),
                () -> race.addCount(),
                () -> assertThat(race.isFinished()).isTrue()
        );
        
    }
}