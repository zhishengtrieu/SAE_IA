package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.*;

public class AStar extends TreeSearch {


    /**
     * Crée un algorithme de recherche
     *
     * @param p Le problème à résoudre
     * @param s L'état initial
     */
    public AStar(SearchProblem p, State s) {
        super(p, s);
    }

    @Override

    public boolean solve() {
        PriorityQueue<SearchNode> openList = new PriorityQueue<>(Comparator.comparingDouble(n -> n.getCost() + n.getHeuristic()));
        Set<State> closedList = new HashSet<>();

        SearchNode startNode = SearchNode.makeRootSearchNode(intial_state);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            SearchNode currentNode = openList.poll();
            State currentState = currentNode.getState();

            if (problem.isGoalState(currentState)) {
                this.end_node = currentNode;
                reconstructPath(currentNode);
                return true;
            }

            closedList.add(currentState);

            for (Action action : problem.getActions(currentState)) {
                State nextState = problem.doAction(currentState, action);
                if (closedList.contains(nextState)) {
                    continue;
                }

                SearchNode nextNode = SearchNode.makeChildSearchNode(problem, currentNode, action);
                if (!openList.contains(nextNode)) {
                    openList.add(nextNode);
                }
            }
        }

        return false;
    }

    private ArrayList<Action> reconstructPath(SearchNode goalNode) {
        ArrayList<Action> path = new ArrayList<>();
        SearchNode currentNode = goalNode;

        // Remonter le chemin depuis le nœud objectif jusqu'à la racine
        while (currentNode != null && currentNode.getParent() != null) {
            path.add(currentNode.getAction());
            currentNode = currentNode.getParent();
        }

        // Inverser la liste pour obtenir le chemin de la racine à l'objectif
        Collections.reverse(path);

        return path;
    }

}
