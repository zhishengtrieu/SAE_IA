package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

public class MinMaxPlayer extends Player {
    public MinMaxPlayer(Game g, boolean p1) {
        super(g, p1);
        name = "MinMax";
    }

    @Override
    public Action getMove(GameState state) {
        return game.getMinMaxMove(state);
    }
}
