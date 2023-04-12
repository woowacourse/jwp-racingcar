package racingcar.service;

class StubGamesRepository implements GamesRepository {

    @Override
    public int save(final int count) {
        return 1;
    }
}
