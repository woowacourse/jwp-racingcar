package racingcar.game.dto;

public class GameRequestDTO {
    
    private final String names;
    private final int count;
    
    public GameRequestDTO(final String names, final int count) {
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
