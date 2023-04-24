package racingcar;

import racingcar.controller.GameController;
public class RacingCarConsoleApplication{
    public static void main(String[] args){
        GameController gameController = new GameController();
        gameController.play();
    }
}
