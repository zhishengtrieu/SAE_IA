package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

public class MinMaxAlphaBetaPlayer extends Player {

    public MinMaxAlphaBetaPlayer(Game g, boolean p1) {
        super(g, p1);
        name = "MinMaxAlphaBeta";
    }

    @Override
    public Action getMove(GameState state) {
        return game.getMinMaxAlphaBetaMove(state);
    }
}
