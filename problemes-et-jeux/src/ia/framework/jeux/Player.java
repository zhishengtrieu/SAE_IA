package ia.framework.jeux;


import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;

/**
 * Définie un joueur
 *
 */

public abstract class Player {

    public static final int PLAYER1 = 0;
    public static final int PLAYER2 = 1;

    protected String name = null;
    
    protected int player;
    
    protected Game game;

    /**
     * Represente un joueur
     *
     * @param g l'instance du jeux
     * @param player_one si joueur 1
     */    
    public Player(Game g, boolean player_one){
        game = g;
        player=PLAYER1;
        if (!player_one)
            player=PLAYER2; 
    }

    public String getName(){
        return name;
    }

    
    /* 
     * Récupère le coup pour ce joueur
     *
     * @return le coup (Action) choisi 
     */
    public abstract Action getMove(GameState state);


    
    
}

