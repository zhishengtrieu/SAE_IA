package ia.framework.common;

import java.util.ArrayList;

/**
 * Représente une abstraction pour un problème ou un jeux
 *
 */

public abstract class BaseProblem {

    /**
     *  <p>La liste de toutes les actions
     *  les classes concrètes doivent le renseigner </p>
     *   Voir {@link ia.problemes.Vacuum} ou {@link ia.problemes.EightPuzzle}
     */
    
    protected static Action[] ACTIONS = null;
       

    /**
     * Retourner les actions possibles un état
     * @param s Un état 
     * @return Les actions possibles (pas forcement toutes) depuis s 
     */
    public abstract ArrayList<Action> getActions(State s);

    /**
     * Exécuter une action dans un état 
     * @param s Un état 
     * @param a Une action
     * @return L'état résultat de faire l'action a dans s
     */
    public abstract State doAction(State s, Action a);

}
