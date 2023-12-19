package ia.problemes;

import ia.framework.common.Action;
import ia.framework.common.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ia.problemes.RushHour.*;

public class RushHourState extends State {
    private char[][] board;
    private final int ROWS = 6;
    private final int COLS = 6;

    public static final char EMPTY = '.';
    public static final char RED = 'R';

    public RushHourState() {
        board = new char[ROWS][COLS];
        initializeBoard();
    }

    public RushHourState(char[][] board) {
        this.board = board;
    }

    public RushHourState(String path) {
        board = new char[ROWS][COLS];
        loadBoard(path);
    }

    public void loadBoard(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            int row = 0;
            while (line != null) {
                line = line.replaceAll("\\s+", "");
                for (int col = 0; col < line.length(); col++) {
                    System.out.println(line.charAt(col));
                    board[row][col] = line.charAt(col);
                }
                line = reader.readLine();
                row++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public State cloneState() {
        char[][] newBoard = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            newBoard[i] = Arrays.copyOf(board[i], COLS);
        }
        return new RushHourState(newBoard);
    }

    @Override
    public boolean equalsState(State other) {
        if (!(other instanceof RushHourState otherState)) {
            return false;
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (this.board[i][j] != otherState.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected int hashState() {
        return Arrays.deepHashCode(board);
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
        /*
        . . A . C C
        . . A . . .
        R R A . . .
        B B B . . D
        . . . . . D
        . . . . . D
         */
        board[2][0] = 'R';
        board[2][1] = 'R';

        board[0][2] = 'A';
        board[1][2] = 'A';
        board[2][2] = 'A';

        board[3][0] = 'B';
        board[3][1] = 'B';
        board[3][2] = 'B';

        board[0][3] = 'C';
        board[0][4] = 'C';

        board[3][5] = 'D';
        board[4][5] = 'D';
        board[5][5] = 'D';
    }

    // Vérifie si l'état est un état gagnant
    public boolean isGoalState() {
        //on part de l'inverse, on vérifie qu'aucun véhicule ne bloque le passage
        for(int i = COLS-1; i > 0; i--){
            if(board[2][i] != EMPTY && board[2][i] != RED){
                System.out.println("Le véhicule " + board[2][i] + " bloque le passage");
                return false;
            }
            if (board[2][i] == RED) {
                System.out.println("Le véhicule " + board[2][i] + " a atteint la sortie");
                printBoard();
                return true;
            }
        }
        return true;
    }

    // Méthode pour afficher l'état du plateau
    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public Set<Character> getVehicles() {
        Set<Character> vehicles = new HashSet<>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                char cell = board[row][col];
                if (cell != EMPTY) {
                    vehicles.add(cell);
                }
            }
        }
        return vehicles;
    }


    // Vérifie si un véhicule peut bouger dans la direction donnée
    public boolean canMove(char vehicle, Action action) {
        // Trouver la position, la taille et l'orientation du véhicule
        int vehicleRow = -1, vehicleCol = -1;
        int vehicleSize = 0;
        boolean isHorizontal = false;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == vehicle) {
                    if (vehicleRow == -1 && vehicleCol == -1) {
                        vehicleRow = row;
                        vehicleCol = col;
                    }
                    vehicleSize++;

                    if (col + 1 < COLS && board[row][col + 1] == vehicle) {
                        isHorizontal = true;
                    }
                }
            }
        }

        // Vérifier la possibilité de mouvement en fonction de l'orientation et de la taille
        if (isHorizontal) {
            if (action.equals(LEFT)) {
                return vehicleCol > 0 && board[vehicleRow][vehicleCol - 1] == EMPTY;
            } else if (action.equals(RIGHT)) {
                return vehicleCol + vehicleSize < COLS && board[vehicleRow][vehicleCol + vehicleSize] == EMPTY;
            }
        } else {
            if (action.equals(UP)) {
                return vehicleRow > 0 && board[vehicleRow - 1][vehicleCol] == EMPTY;
            } else if (action.equals(DOWN)) {
                return vehicleRow + vehicleSize < ROWS && board[vehicleRow + vehicleSize][vehicleCol] == EMPTY;
            }
        }

        return false;
    }

    // Déplace un véhicule dans la direction donnée
    public void moveVehicule(char vehicle, Action action) {
        if (!canMove(vehicle, action)) {
            return;
        }
        printBoard();
        int deltaRow = 0, deltaCol = 0;
        if (action.equals(UP)) deltaRow = -1;
        else if (action.equals(DOWN)) deltaRow = 1;
        else if (action.equals(LEFT)) deltaCol = -1;
        else if (action.equals(RIGHT)) deltaCol = 1;

        // Identifier les cases occupées par le véhicule
        ArrayList<int[]> vehiclePositions = new ArrayList<>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == vehicle) {
                    vehiclePositions.add(new int[]{row, col});
                    board[row][col] = EMPTY;  // Libérer l'ancienne position
                }
            }
        }

        // Déplacer le véhicule
        for (int[] position : vehiclePositions) {
            int newRow = position[0] + deltaRow;
            int newCol = position[1] + deltaCol;
            board[newRow][newCol] = vehicle;
        }
    }

}
