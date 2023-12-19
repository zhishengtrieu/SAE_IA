import benchmark.AlphaBetaBenchmark;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.problemes.ConnectFour;

public class MeasureAlphaBeta
{
    public static void main(String[] args) {
        Game game = new ConnectFour(6, 7);
        GameState state = game.init();
        AlphaBetaBenchmark benchmark = new AlphaBetaBenchmark(game, state);
        benchmark.runAndPlot();
    }
}
