package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.LinkedList;

public class DFS_H extends TreeSearch {
    public DFS_H(SearchProblem problem, State initialState) {
        super(problem, initialState);
        this.frontier = new LinkedList<>();
    }

    @Override
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