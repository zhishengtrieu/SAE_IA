package ia.framework.jeux;

import java.util.ArrayList;
import java.util.Random;

import ia.framework.common.ActionValuePair;
import ia.framework.common.BaseProblem;
import ia.framework.common.Action;
import ia.framework.common.State;

/**
 * Définie un problem de type jeux
 *
 * est un BaseProblem sans tableau d'état ni transitions
 * on les genère dans l'état du jeux comme pour SearchProblem
 */

public abstract class Game extends BaseProblem {

    public static final double MIN_VALUE = Double.MIN_VALUE;
    public static final double MAX_VALUE = Double.MAX_VALUE;

    // pour générer des coups aléatoires
    private Random rng = new Random(); 


    /**
     * Crée l'état initial du jeux
     *
     * @return un état du jeux vide
     */
    public abstract GameState init();

    
    
    /**
     *retourne vrai si on arrive en fin de partie
     * 
     * @param s l'état du jeux
     * @return vrai si fin du jeux
     */
    
    public abstract boolean endOfGame(GameState s);

    /**
     * Demander à l'utilisateur son coup (dépend du jeux)
     *
     * @param s l'état du jeux
     * @return l'action choisie par l'utilisateur
     */ 
    public abstract Action getHumanMove(GameState s);


    /**
     * Générer un coup  aléatoire
     *
     * @param s l'état du jeux
     * @return l'action générée
     */ 
    public Action getRandomMove(GameState s){
        ArrayList<Action> possible = getActions((State) s);
        if( possible.size()>0 )
            return possible.get(rng.nextInt(possible.size()));
        return null; 
    }

    /**
     * Fonction MinMax (Un jeux G, Un état du jeux S) retourne un coup
     *
     *  si tour du joueur 1                           // si c'est au tour de max
     *     (valeur, coup) = MaxValeur(S)              // on commence par appeler MaxValeur sinon
     *  sinon                                         // on appelle MinValeur
     *     (valeur, coup) = MinValeur(S)
     *  retourner coup                                // la récursion est fini on retourne le coup à jouer
     *
     *
     * @param state
     * @return
     */
    public Action getMinMaxMove(GameState state) {
        if(state.getPlayerToMove()==1) {
            return MaxValeur(state).getAction();
        } else {
            return MinValeur(state).getAction();
        }
    }

    public Action getMinMaxAlphaBetaMove(GameState state) {
        if(state.getPlayerToMove()==1) {
            return MaxValeur(state, MIN_VALUE, MAX_VALUE).getAction();
        } else {
            return MinValeur(state, MIN_VALUE, MAX_VALUE).getAction();
        }
    }

    /**
     * Fonction MaxValeur(Un état du jeux S) retourne un couple (valeur, coup) // le meilleur coup du point de de vue de max
     *
     *  Si fin de partie
     *    retourner (V_max, NULL)
     *  V_max = -INFINI                               // Initialisation de la meilleur valeur du jeux (pour max)
     *  C_max = NULL                                  // le coup qui mène à cette valeur
     *
     *   Pour tout les coups c possibles depuis S
     *     S_suivant = jouer c dans S                 // On joue le coup et récupère un nouvelle état
     *     (v, c) = MinValeur(S_suivant)              // On alterne à min
     *     si v > V_max                               // Mise à jour de la meilleur valeur et du coup
     *       V_max = v
     *       C_max = c
     *
     *   retourner (V_max, C_max)
     * @param state
     * @return
     */
    private ActionValuePair MaxValeur(GameState state) {
        if (state.getGameValue()>=0){
            return new ActionValuePair(null, state.getGameValue());
        } else {
            double V_max = Double.NEGATIVE_INFINITY;
            Action C_max = null;
            for (Action c : getActions(state)) {
                GameState S_suivant = (GameState) doAction(state, c);
                ActionValuePair v = MinValeur(S_suivant);
                if (v.getValue() > V_max) {
                    V_max = v.getValue();
                    C_max = c;
                }
            }
            return new ActionValuePair(C_max, V_max);
        }

    }

    private ActionValuePair MaxValeur(GameState state, double alpha, double beta) {
        if (state.getGameValue()>=0){
            return new ActionValuePair(null, state.getGameValue());
        } else {
            double V_max = Double.NEGATIVE_INFINITY;
            Action C_max = null;
            for (Action c : getActions(state)) {
                GameState S_suivant = (GameState) doAction(state, c);
                ActionValuePair v = MinValeur(S_suivant);
                if (v.getValue() > V_max) {
                    V_max = v.getValue();
                    C_max = c;

                    if (V_max > alpha) {
                        alpha = V_max;
                    }
                }
                if (V_max >= beta) {
                    return new ActionValuePair(C_max, V_max);
                }
            }
            return new ActionValuePair(C_max, V_max);
        }

    }

    /**
     *
     *
     * Fonction MinValeur(Un état du jeux S) retourne un couple (valeur, coup)  // le meilleur coup du point de vue de Min
     *
     *       Si fin de partie
     *         retourner (V_min, NULL)
     *       V_min = +INFINI                              // Différence par rapport à MaxValeur
     *       C_min = NULL
     *
     *       Pour tout les coups possibles c depuis S
     *         S_suivant = jouer c dans S
     *         (v, c) = MaxValeur(S_suivant)
     *         si v < V_min                               // Différence par rapport a MaxValeur
     *           V_min = v
     *           C_min = c
     *
     *       retourner (V_min, C_min)
     */
    private ActionValuePair MinValeur(GameState state) {
        if (state.getGameValue()>=0){
            return new ActionValuePair(null, state.getGameValue());
        } else {
            double V_min = Double.POSITIVE_INFINITY;
            Action C_min = null;
            for (Action c : getActions(state)) {
                GameState S_suivant = (GameState) doAction(state, c);
                ActionValuePair v = MaxValeur(S_suivant);
                if (v.getValue() < V_min) {
                    V_min = v.getValue();
                    C_min = c;
                }
            }
            return new ActionValuePair(C_min, V_min);
        }
    }

    private ActionValuePair MinValeur(GameState state, double alpha, double beta) {
        if (state.getGameValue()>=0){
            return new ActionValuePair(null, state.getGameValue());
        } else {
            double V_min = Double.POSITIVE_INFINITY;
            Action C_min = null;
            for (Action c : getActions(state)) {
                GameState S_suivant = (GameState) doAction(state, c);
                ActionValuePair v = MaxValeur(S_suivant);
                if (v.getValue() < V_min) {
                    V_min = v.getValue();
                    C_min = c;

                    if (V_min < beta) {
                        beta = V_min;
                    }
                }
                if (V_min <= alpha) {
                    return new ActionValuePair(C_min, V_min);
                }
            }
            return new ActionValuePair(C_min, V_min);
        }
    }


}
