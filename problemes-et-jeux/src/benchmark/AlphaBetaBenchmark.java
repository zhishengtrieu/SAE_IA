package benchmark;

import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendPosition;

import java.util.ArrayList;
import java.util.List;

public class AlphaBetaBenchmark {

    private Game game;
    private GameState state;

    public AlphaBetaBenchmark(Game game, GameState state) {
        this.game = game;
        this.state = state;
    }

    public void runAndPlot() {
        int[] depths = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long[] times = new long[depths.length];

        for (int i = 0; i < depths.length; i++) {
            long start = System.nanoTime();
            game.getMinMaxAlphaBetaMove(state, depths[i]);
            long end = System.nanoTime();
            times[i] = end - start;
        }

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("AlphaBeta Execution Time").xAxisTitle("Depth").yAxisTitle("Time (ns)").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
//        chart.getStyler().setHasAnnotations(true);

        // Series
        List<Integer> depthList = new ArrayList<>();
        for (int depth : depths) {
            depthList.add(depth);
        }

        List<Long> timeList = new ArrayList<>();
        for (long time : times) {
            timeList.add(time);
        }

        chart.addSeries("AlphaBeta", depthList, timeList);


        // Show it
        new SwingWrapper(chart).displayChart();
    }
}
