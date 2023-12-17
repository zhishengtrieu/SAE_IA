package ia.framework.jeux;

import ia.framework.common.State;

/**
 * Represente un etat d'un jeux
 *
 * Un etat (State) avec une valeur
 */
  
public abstract class GameState extends State {

        
    protected double game_value;
    protected int player_to_move;
    
    /** 
     * La valeur de la partie {-1, 1, 0.5, 0}
     * 
     * <p>-1  : si partie pas fini
     *  1  : si le joueur 1 a gagné
     * 0.5 : si match nul
     *  0  : si joueur 2 a gagné</p>
     *
     * @return la valeur de la partie
     */
    public double getGameValue (){
        return game_value;
	}

    /**
     * Retourne a qui de jouer
     *
     * @return le jouer a jouer
     */
    public int getPlayerToMove() {
		return player_to_move;
	}

    /**
     * {@inheritDoc}
     */
    public boolean equalsState(State o) {
        GameState other = (GameState) o;
        return game_value==other.getGameValue() &&
            player_to_move==other.getPlayerToMove();
    }
}
