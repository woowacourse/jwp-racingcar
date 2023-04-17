package racingcar.service;

class RacingcarServiceTest {
    /*@ParameterizedTest
    @DisplayName("우승자 확인하기")
    @CsvSource("car1,car2,car3")
    void findWinner(String car1, String car2, String car3) {
        RacingcarService racingcarService = new RacingcarService(Arrays.asList(car1, car2, car3));

        List<Car> winnersCars = racingcarService.findWinners();

        assertAll(
                () -> assertThat(winnersCars.get(0).getName()).isEqualTo(car1),
                () -> assertThat(winnersCars.get(1).getName()).isEqualTo(car2),
                () -> assertThat(winnersCars.get(2).getName()).isEqualTo(car3)
        );
    }

    @Test
    @DisplayName("경주 참여 인원 두명 미만인 경우 예외")
    void validateParticipants() {
        assertThatThrownBy(() -> new RacingcarService(Arrays.asList("car1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 경주는 최소 2명이 필요해요.");
    }*/
}
