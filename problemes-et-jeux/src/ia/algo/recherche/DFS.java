package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.LinkedList;

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
        ((LinkedList<SearchNode>)frontier).addFirst(SearchNode.makeRootSearchNode(intial_state));
        while (!frontier.isEmpty()) {
            SearchNode node = ((LinkedList<SearchNode>)frontier).removeFirst();
            if (problem.isGoalState(node.getState())) {
                end_node = node;
                return true;
            }
            explored.add(node.getState());
            for (Action action : problem.getActions(node.getState())) {
                SearchNode child = SearchNode.makeChildSearchNode(problem, node, action);
                if (!explored.contains(child.getState()) && !frontier.contains(child)) {
                    ((LinkedList<SearchNode>)frontier).addFirst(child);
                }
            }
        }
        return false;
    }
}
