package ia.problemes;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.Problem;

/**
 * Représente un problème générique
 * <p>Le problème est un graphe aléatoire de taille
 * spécifiée par les constantes de la classe.</p>
 *
 */

public class Dummy extends Problem {

    /**
     * Le nombre de noeud du graphe (taille du problème)
     */
    public static final int SIZE = 100;

    /**
     * La probabilité de créer un transition
     * <p>Peut être considéré comme un facteur de
     * branchement moyen.</p>
     */
    public static final double PROB = .15;

    /**
     * Crée un problème représenter par un graphe aléatoire.
     * 
     */
    
    public Dummy(){

        Random rng = new Random(1234);
        
        // Créer quelques états et quelques actions

        STATES = new DummyState[SIZE];
        ACTIONS = new Action[SIZE];
        for(int i=0; i<SIZE; i++){
            STATES[i] = new DummyState(i);
            ACTIONS[i] = new Action("Goto "+ STATES[i]);
        }

        // Créer des transitions aléatoires

        while( !checkConnected() ){ // le graphe doit être connecté !
            for(int i=0; i<SIZE; i++){
                DummyState s1 = (DummyState) STATES[i];
                for(int j=0; j<SIZE; j++){
                    DummyState s2 = (DummyState) STATES[j];

                    // Crée le lien avec une prob
                    
                    if(rng.nextFloat() < PROB){
                        Action a1 = ACTIONS[i]; // goto s1
                        Action a2 = ACTIONS[j]; // goto s2
                        int cost = rng.nextInt(11)+10;//dans [10,20]

                        // on met des action de s1->s2 et l'inverse
                        TRANSITIONS.addTransition(s1, a2, s2, cost);
                        TRANSITIONS.addTransition(s2, a1, s1, cost);
                    }
                }
            }
        }
    }

    /**
     * L'état but est le dernier état
     */
    public boolean isGoalState(State s){
        return s.equals(STATES[SIZE-1]);
    }
    
    // vérifier que le graphe est connecté
    
    private boolean checkConnected(){
        for(int i=0; i<SIZE; i++)
            if (getActions(STATES[i]).size() == 0)
                return false;
        return true;
    }
}
