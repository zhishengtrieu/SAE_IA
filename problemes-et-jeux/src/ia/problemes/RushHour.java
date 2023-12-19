package ia.problemes;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.Problem;

import java.util.ArrayList;

public class RushHour extends Problem {

    public static final Action UP = new Action("Up");
    public static final Action LEFT = new Action("Left");
    public static final Action DOWN = new Action("Down");
    public static final Action RIGHT = new Action("Right");

    public RushHour() {
        super();
    }

    @Override
    public ArrayList<Action> getActions(State s) {
        RushHourState rushHourState = (RushHourState) s;
        ArrayList<Action> actions = new ArrayList<>();
        for (char vehicule : rushHourState.getVehicles()) {
            if (rushHourState.canMove(vehicule, UP)) {
                actions.add(new Action(vehicule + "-Up"));
            }
            if (rushHourState.canMove(vehicule, DOWN)) {
                actions.add(new Action(vehicule + "-Down"));
            }
            if (rushHourState.canMove(vehicule, LEFT)) {
                actions.add(new Action(vehicule + "-Left"));
            }
            if (rushHourState.canMove(vehicule, RIGHT)) {
                actions.add(new Action(vehicule + "-Right"));
            }
        }
        return actions;
    }

    @Override
    public State doAction(State s, Action a) {
        RushHourState rushHourState = (RushHourState) ((RushHourState) s).cloneState();
        //on récupère le véhicule à bouger et la direction
        String[] action = a.getName().split("-");

        char vehicule = action[0].charAt(0);
        Action direction = new Action(action[1]);
        rushHourState.moveVehicule(vehicule, direction);
        return rushHourState;
    }


    @Override
    public boolean isGoalState(State s) {
        return ((RushHourState) s).isGoalState();
    }

    /**
     * Chaque mouvement coûte 1
     */
    public double getActionCost(State s, Action a) {
        return 1.0;
    }


}
