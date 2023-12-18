package ia.problemes;

import java.util.ArrayList;
import java.util.Scanner;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;

/**
 * Classe qui represente le jeux du morpion 
 */
public class TicTacToe extends Game {

    public TicTacToe() {
        // Construire le tableaux d'actions  
        // 9 cases (pas forcement toutes possibles)
        // 0 la case en haut a gauche et 8 en bas a droite

        ACTIONS = new Action[9];
        for(int i=0; i<9; i++)
            ACTIONS[i] = new Action(Integer.toString(i));
    }

    /**
     * {@inheritDoc}
     * <p>Crée une grille de morpion vide</p>
     */
    public GameState init(){
        return new TicTacToeState();
    }
    
    /**
     * {@inheritDoc}
     * <p>retourne les actions possibles en fonction 
     * du joueur et de l'état du jeux</p>
     */
    public ArrayList<Action> getActions(State s){
        ArrayList<Action> actions = new ArrayList<Action>();
        for (Action a : ACTIONS){
            int action_idx = Integer.parseInt(a.getName());
            TicTacToeState game_state = (TicTacToeState) s;
            if( game_state.isLegal(action_idx) )
                actions.add(a);
        }
        return actions;
    }

    
    /**
     * {@inheritDoc}
     * <p>retourne retourn la nouvelle grille après l'action</p>
     */
    public State doAction(State s, Action a){
        TicTacToeState new_s = (TicTacToeState) s.clone();
        int action_idx = Integer.parseInt(a.getName());
        new_s.play(action_idx);
        return new_s;
    }

    // fin dès qu'il y a une ligne ou plus de place de jeux
    public boolean endOfGame(GameState s){
        return ((TicTacToeState) s).lineThroughBoard() ||
            getActions(s).size()==0;
    }

    // retourne une action depuis une entrée utilisateur
    public Action getHumanMove(GameState state){
        
        Scanner in = new Scanner(System.in);
        Boolean played = false;
        
        while( !played ){ 
            System.out.print("A toi de jouer [0 à 8] > ");
            try{
                int move = in.nextInt();
                if (move >=0 && move <9 &&
                    ((TicTacToeState) state).isLegal(move))
                    return ACTIONS[move];
                else 
                    System.out.println("Invalide !");
            
            } catch(java.util.InputMismatchException e) {
                in.nextLine();
                System.out.println("Invalide !");
            }
        }
        
        return null; // faire plaisir a javac
    }


    
}
