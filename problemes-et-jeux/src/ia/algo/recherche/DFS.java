package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

public class DFS extends TreeSearch {

    public DFS(SearchProblem prob, State intial_state){
        super(prob, intial_state);
    }

    @Override
    /**
     * parcourt en profondeur
     * structure LIFO
     * procedure DFS(G, v) is
     *     label v as discovered
     *     for all directed edges from v to w that are in G.adjacentEdges(v) do
     *         if vertex w is not labeled as discovered then
     *             recursively call DFS(G, w)
     */
    public boolean solve() {
        SearchNode node = SearchNode.makeRootSearchNode(intial_state);
        State state = node.getState();
        frontier.add(node);
        explored.add(state);
        while (!frontier.isEmpty()) {
            node = frontier.poll();
            state = node.getState();
            if (problem.isGoalState(state)) {
                end_node = node;
                return true;
            }
            else {
                for (Action a : problem.getActions(state)) {
                    SearchNode child = SearchNode.makeChildSearchNode(problem, node, a);
                    State childState = child.getState();
                    if (!explored.contains(childState)) {
                        frontier.add(child);
                        explored.add(childState);
                    }
                }
            }
        }
        return false;
    }
}
