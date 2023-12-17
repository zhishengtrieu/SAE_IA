package ia.framework.jeux;

import java.util.ArrayList;
import java.util.Random;

import ia.framework.common.BaseProblem;
import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.common.ArgParse;

/**
 * Définie un gestionaire du jeux (Moteur de jeux)
 *
 * <p>Contien et manipule un jeux et deux joueur</p>
 */

public class GameEngine {

    private Game game;
    private Player player1;
    private Player player2;
    private int total_moves = 0;
    
    /**
     * Crée une instance de moteur de jeux
     * 
     * @param g le jeux
     * @param p1 le joueur 1
     * @param p2 le joueur 2
     */
    
    public GameEngine(Game g, Player p1, Player p2){
        game = g;
        player1 = p1;
        player2 = p2;
    }


    /**
     * Le nombre total de coups jusqu'a la fin de partie
     *
     * @return le nombre de coups  
     */
    public int getTotalMoves(){
        return total_moves;
    }
  

    /**
     * La boucle du jeux
     * 
     * <p> récupère les actions des joueurs et les réalisent jusqu'à
     * la fin de la partie </p>
     */
    public GameState gameLoop(){


        // Créer l'état de départ
        
        GameState state = game.init();
        
        while(true){
            
            // récupère le coup du joueur 1 et le jouer
            System.out.println("Au joueur 1 de jouer: ");
            Action p1_move = player1.getMove( state );
            if(ArgParse.DEBUG)
                System.out.println("\tmoved at : "+p1_move );

            GameState new_state =
                (GameState) game.doAction(state, p1_move);

            System.out.println(new_state);
            if(ArgParse.DEBUG) 
                System.out.println("\tVal= "+new_state.getGameValue());
            

            // est-ce que c'est fini
            if(endOfGame(new_state)){
                state = new_state;
                total_moves ++;
                break;
            }
            
            // récupère le coup du joueur 2 et le jouer
            System.out.println("Au joueur 2 de jouer: ");
            Action p2_move = player2.getMove( new_state );
            if(ArgParse.DEBUG)
                System.out.println("\tmoved at : "+p2_move );

            
            state = (GameState) game.doAction(new_state, p2_move);
            System.out.println(state);
            if(ArgParse.DEBUG)
                System.out.println("\tVal= "+state.getGameValue());
            
            
            // est-ce que c'est fini
            if(endOfGame(state)){
                total_moves ++;
                break;
            }
            
            total_moves++;
        }

        System.out.println("Fin de partie");
        
        return state;

    }


    /**
     * Test de fin de partie
     * 
     * @return vrai si fin de partie 
     */
    private boolean endOfGame(GameState state) {
        return game.endOfGame(state);
    }
    
 
    /**
     * Réupère le gagnant / ou null
     *
     * @param s l'état (final) du jeux
     * @return le gagnant 
     */ 
    public Player getWinner(GameState s){
        double value = s.getGameValue();

        if(value == 1)
            return player1;
        if(value == 0)
            return player2;
        
        return null;
    }
    
    /**
     * Réupère le gagnant / ou null
     *
     * @param s l'état (final) du jeux
     * @return la valeur de fin du jeux
     */ 
    public double getEndGameValue(GameState s){
        return s.getGameValue();
    }
    
}
