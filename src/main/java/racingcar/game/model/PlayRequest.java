package racingcar.game.model;

public class PlayRequest {
    
    private final String names;
    private final int count;
    
    public PlayRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public String getNames() {
        return this.names;
    }
}
