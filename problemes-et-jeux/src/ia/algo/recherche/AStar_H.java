package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.HasHeuristic;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar_H extends TreeSearch {

    public AStar_H(SearchProblem problem, State initialState) {
        super(problem, initialState);
    }

    @Override
    public boolean solve() {
        PriorityQueue<SearchNode> frontier = new PriorityQueue<>(Comparator.comparingDouble(node -> ((HasHeuristic) node.getState()).getHeuristic() + node.getCost()));
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
                } else if (frontier.contains(child) && child.getCost() < node.getCost()) {
                    frontier.remove(node);
                    frontier.add(child);
                }
            }
        }
        return false;
    }

}

