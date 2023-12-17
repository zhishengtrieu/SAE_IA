package ia.problemes;

import java.util.Arrays;
import java.util.ArrayList;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.jeux.GameState;

/**
 * Représente un état du jeux du morpion 
 *
 */

public class TicTacToeState extends GameState {

    public static final int O = 'O';
	public static final int X = 'X';
	public static final int EMPTY = ' ';
    
    private char[] board = null;

    /**
     * Construire une grille vide
     *
     */
    public TicTacToeState() {
        board = new char[] { EMPTY, EMPTY, EMPTY,
                             EMPTY, EMPTY, EMPTY,
                             EMPTY, EMPTY, EMPTY };

        // -1: pas fini,1: X gagne, 0: O Gagne, 0.5 match nul
        game_value = -1; 
        player_to_move = X; // convention X comence 
    }

    /**
     * Crée une grille depuis une autre grille 
     *
     * @param b une grille de jeux
     */
    public TicTacToeState(char[] b) {
        board = b.clone();
        player_to_move = X;
	}

    /**
     * Retourne la grille
     *
     * @return la grille du jeux
     */
    public char[] getBoard(){
        return board;
    }

    public TicTacToeState cloneState() {
        TicTacToeState new_s = new TicTacToeState(board);
        new_s.player_to_move = player_to_move;
        new_s.game_value = game_value;
        return new_s;
	}

	public boolean equalsState(State o) {
        TicTacToeState other = (TicTacToeState) o;
        return Arrays.equals(board, other.getBoard()) &&
            super.equalsState(o);
	}

    public int hashState() {
        int result = 31 * Arrays.hashCode(board);
        result = 31 * result + Integer.hashCode(player_to_move);
        result = 31 * result + Double.hashCode(game_value);
		return result;
	}

    @Override
	public String toString() {
        
        String res = "    +---+---+---+\n"
            //+"|  0|  1|  2|\n"
            +"0-2 | " +board[0] + " | " + board[1] + " | " + board[2] + " |\n"
            +"    +---+---+---+\n"
            //+"|  3|  4|  5|\n"
            +"3-5 | " +board[3] + " | " + board[4] + " | " + board[5] + " |\n"
            +"    +---+---+---+\n"
            //+"|  6|  7|  8|\n"
            +"6-8 | " +board[6] + " | " + board[7] + " | " + board[8]+ " |\n"
            +"    +---+---+---+\n";
        //return Arrays.toString(board);
        // String s = "";
        // for (int i=0, i<9; i++){
        //     s+="   |";
        //     s+=" "+board[0
        //     s+="   |"; 
            

            return res;
	}

     /**
     * on peut jouer là ?
     *
     * @param idx indice de la case
     * @return vrai si case vide
     */ 
    public boolean isLegal(int idx) {
        return board[idx]==EMPTY;
	}
    
    /**
     * Le joueur courant (player_to_move) joue à la case idx
     *
     * @param idx l'index de la case
     */
	public void play(int idx) {
        if (game_value == -1 && board[idx]==EMPTY) {
			board[idx] = (char) player_to_move;
			updateGameValue();
			player_to_move = (player_to_move==X ? O : X); 
		}
	}
    
    /**
     * vérifier s'il y a pas une ligne de faite (fin du jeux)
     *
     * @return vrai si partie fini 
     */
	public boolean lineThroughBoard() {
		return (isAnyRowComplete() ||
                isAnyColumnComplete() ||
                isAnyDiagonalComplete());
	}

    
    // L'API privé 
       
	//verifier s'il y a pas une ligne horizontale de faite
	private boolean isAnyRowComplete() {
		for (int row = 0; row < 3; row++) {
			char val = getValueAt(0, row);
			if ((val!=EMPTY) && val==getValueAt(1, row) &&
                val==getValueAt(2, row)) {
                return true;
			}
		}
		return false;
	}
    
    // verifier s'il y a pas une ligne vertical de faite
	private boolean isAnyColumnComplete() {
		for (int col = 0; col < 3; col++) {
			char val = getValueAt(col, 0);
			if (val!=EMPTY && val==getValueAt(col, 1) &&
                val==getValueAt(col, 2)) {
				return true;
			}
		}
		return false;
	}
    
    // verifier s'il y a pas une ligne diagonale de faite
	private boolean isAnyDiagonalComplete() {
		char val = getValueAt(0, 0);
		if (val!=EMPTY && val==getValueAt(1, 1) &&
            val==getValueAt(2, 2)) {
			return true;
		}
		val = getValueAt(0, 2);
		if (val!=EMPTY && val==getValueAt(1, 1) &&
            val==getValueAt(2, 0)) {
			return true;
		}
		return false;
	}

    // Mettre a jour la valeur de la partie 
    protected  void updateGameValue() {
		if (lineThroughBoard()) { // un gagnant, lequel ? 
			game_value = player_to_move==X ? 1 : 0;
		} else if (getNumberOfMarkedPositions() == 9) { // nule
			game_value = 0.5; 
		}
	}
    // retourne la valeur au coordonnées données
    private char getValueAt(int col, int row) {
		return board[getPositionAt(col, row)];
	}

    // retourne l'index de la case au coordonnées données
	private int getPositionAt(int col, int row) {
		return row * 3 + col;
	}

    // le nombre de case déjà remplies
    private int getNumberOfMarkedPositions() {
        int count = 0;
        for(int i=0; i<9; i++)
            if (board[i] != EMPTY)
					count++;
        return count;
    }
    
    
}
