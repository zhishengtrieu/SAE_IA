package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UCS extends TreeSearch {
    /**
     * Crée un algorithme de recherche
     *
     * @param problem Le problème à résoudre
     * @param initialState L'état initial
     */
    public UCS(SearchProblem problem, State initialState) {
        super(problem, initialState);
        Comparator<SearchNode> comparator = Comparator.comparing(SearchNode::getCost);
        this.frontier = new PriorityQueue<>(comparator);
    }

    @Override
    public boolean solve() {
        PriorityQueue<SearchNode> frontier = new PriorityQueue<>(Comparator.comparingDouble(SearchNode::getCost));
        frontier.add(SearchNode.makeRootSearchNode(intial_state));
        while (!frontier.isEmpty()) {
            SearchNode node = frontier.poll();
            if (problem.isGoalState(node.getState())) {
                end_node = node;
                return true;
            }
            explored.add(node.getState());
            for (Action action : problem.getActions(node.getState())) {
                SearchNode child = SearchNode.makeChildSearchNode(problem, node, action);
                if (!explored.contains(child.getState()) && !frontier.contains(child)) {
                    frontier.add(child);
                }
            }
        }
        return false;
    }

}
