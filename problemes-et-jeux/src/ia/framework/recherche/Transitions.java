package ia.framework.recherche;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import ia.framework.common.State;
import ia.framework.common.Action;
import ia.framework.common.StateActionPair;

/**
 * Représentes la liste des transisions entre états
 *  
 *
 */

public class Transitions {

    private Map<StateActionPair, State> transition =
        new HashMap<StateActionPair, State>();

    private Map<StateActionPair, Double> costs =
        new HashMap<StateActionPair, Double>();

    private Set<StateActionPair> keys = null;

    // Ajoute un transition a la liste de transitions
    
    public void addTransition(State s1, Action a, State s2, double c){
        this.transition.put(new StateActionPair(s1, a), s2);
        this.costs.put(new StateActionPair(s1, a), c);
    }

    public State getTransition(State s, Action a){
        return this.transition.get(new StateActionPair(s, a));
    }
    public double getCost(State s, Action a){
        return this.costs.get(new StateActionPair(s, a));
    }

    // retourn les couples (état, action) de la liste 
    public Set<StateActionPair> getKeys(){
        if(this.keys == null)
            this.keys =  this.transition.keySet();
        return this.keys;
        
    }



    
}
