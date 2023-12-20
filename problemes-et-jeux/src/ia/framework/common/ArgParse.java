package ia.framework.common;

import java.util.Arrays;

import ia.framework.common.*;
import ia.framework.recherche.*;
import ia.framework.jeux.*;
import ia.problemes.*;
import ia.algo.recherche.*;
import ia.algo.jeux.*;

/**
 * Quelques méthodes rudimentaires pour lire la ligne de commande
 * et lancer le Schmilblick
 */

public class ArgParse {

    /**
     * Pour afficher plus de chose
     */
    public static boolean DEBUG = false;

    /**
     * Stock le message d'aide
     */
    public static String msg = null;

    /**
     * spécifie le message d'aide
     */
    public static void setUsage(String m) {
        msg = m;
    }

    /**
     * Affiche un message d'utilisation
     */
    public static void usage() {
        System.err.println(msg);
    }

    /**
     * Retourne la valeur d'un champ demandé
     *
     * @param args Le tableau de la ligne de commande
     * @param arg  Le paramètre qui nous intéresse
     * @return La valeur du paramètre
     */
    public static String getArgFromCmd(String[] args, String arg) {
        if (args.length > 0) {
            int idx = Arrays.asList(args).indexOf(arg);
            if (idx % 2 == 0 && idx < args.length - 1)
                return args[idx + 1];
            if (idx < 0)
                return null;
            usage();
        }
        return null;

    }

    /**
     * Pour vérifier l'existence d'une option donnée
     *
     * @param args Le tableau de la ligne de commande
     * @param arg  L'option qui nous intéresse
     * @return vrai si l'option existe
     */
    public static boolean getFlagFromCmd(String[] args, String arg) {
        int idx = Arrays.asList(args).indexOf(arg);
        if (idx >= 0)
            return true;
        return false;

    }

    /**
     * Retourne le nom problème choisi
     *
     * @param args Le tableau de la ligne de commande
     * @return le nom du problème ou null
     */
    public static String getProblemFromCmd(String[] args) {
        handleFlags(args);
        return getArgFromCmd(args, "-prob");
    }

    /**
     * Retourne le nom du jeux choisi
     *
     * @param args Le tableau de la ligne de commande
     * @return le nom du jeux ou null
     */
    public static String getGameFromCmd(String[] args) {
        handleFlags(args);
        return getArgFromCmd(args, "-game");
    }

    /**
     * Retourne le nom l'algorithme choisi
     *
     * @param args Le tableau de la ligne de commande
     * @return le nom de l'algorithme ou null
     */
    public static String getAlgoFromCmd(String[] args) {
        handleFlags(args);

        return getArgFromCmd(args, "-algo");
    }

    /**
     * Retourne le type joueur 1
     *
     * @param args Le tableau de la ligne de commande
     * @return le jeureur 1  ou null
     */
    public static String getPlayer1FromCmd(String[] args) {
        handleFlags(args);
        return getArgFromCmd(args, "-p1");
    }

    /**
     * Retourne le type joueur 2
     *
     * @param args Le tableau de la ligne de commande
     * @return le jeureur 2  ou null
     */
    public static String getPlayer2FromCmd(String[] args) {
        handleFlags(args);
        return getArgFromCmd(args, "-p2");
    }

    /**
     * Traitement des options -v, -h
     *
     * @param args Le tableau de la ligne de commande
     */
    public static void handleFlags(String[] args) {
        DEBUG = getFlagFromCmd(args, "-v");
        if (getFlagFromCmd(args, "-h")) {
            usage();
            System.exit(0);
        }
    }


    /**
     * Factory qui retourne une instance du problème choisie ou celui par défaut
     *
     * @param prob Le nom du problème ou null
     * @return Une instance du problème
     */

    public static SearchProblem makeProblem(String prob) {
        if (prob == null)
            prob = "vac";
        switch (prob) {
            case "dum":
                return new Dummy();
            case "map":
                return new RomaniaMap();
            case "vac":
                return new Vacuum();
            case "puz":
                return new EightPuzzle();
            case "rush":
                return new RushHour();
            default:
                System.out.println("Problème inconnu");
                usage();
                System.exit(1);
        }

        return null; // inatteignable, faire plaisir a javac
    }


    /**
     * Factory qui retourne une instance du problème choisie ou celui par défaut
     *
     * @param game Le nom du problème ou null
     * @return Une instance du problème
     */

    public static Game makeGame(String game) {
        if (game == null)
            game = "tic";
        switch (game) {
            case "tic":
                return new TicTacToe();
            case "c4":
                return new ConnectFour(6, 7);
            default:
                System.out.println("Jeux inconnu");
                usage();
                System.exit(1);
        }

        return null; // inatteignable, faire plaisir a javac
    }

    /**
     * Factory qui retourne une instance du problème choisie ou celui par défaut
     *
     * @param p_type le type de joueur
     * @param game   instance du jeux
     * @param p1     vrai si joueur num 1
     * @return Une instance de player
     */
    public static Player makePlayer(String p_type, Game game, boolean p1) {
        if (p_type == null)
            p_type = "rnd";
        switch (p_type) {
            case "rnd":
                return new RandomPlayer(game, p1);
            case "hum":
                return new HumanPlayer(game, p1);
            case "minmax":
                return new MinMaxPlayer(game, p1);
            case "alphabeta":
                return new MinMaxAlphaBetaPlayer(game, p1);
            default:
                System.out.println("Joueur inconnu");
                usage();
                System.exit(1);
        }

        return null; // inatteignable, faire plaisir a javac
    }


    /**
     * Factory qui retourne une instance de l'algorithme choisi ou celui par défaut
     *
     * @param algo Le nom de l'algorithme ou null
     * @param p    Le problème a résoudre
     * @param s    L'état initial
     * @return Une instance de l'algorithme
     */
    public static TreeSearch makeAlgo(String algo,
                                      SearchProblem p,
                                      State s) {
        if (algo == null)
            algo = "rnd";
        switch (algo) {
            case "rnd":
                return new RandomSearch(p, s);
            case "dfs":
                return new DFS(p, s);
            case "astar":
                return new AStar(p, s);
            case "bfs":
                return new BFS(p, s);
            case "ucs":
                return new UCS(p, s);
            case "gfs":
                return new GFS(p, s);
            default:
                System.out.println("Algorithme inconnu");
                usage();
                System.exit(1);
        }
        return null;  // inatteignable, faire plaisir a javac
    }

    /**
     * Factory qui retourne une instance de l'état initial du problème choisi
     *
     * @param prob Le nom du problème ou null
     * @return L'état initial qui peut être fixé ou généré aléatoirement
     */
    public static State makeInitialState(String prob) {
        if (prob == null)
            prob = "vac";

        return switch (prob) {
            case "dum" -> new DummyState();
            case "map" -> RomaniaMap.ARAD;
            case "puz" -> new EightPuzzleState();
            case "rush" -> new RushHourState("problemes-et-jeux/gameStates/rushHour/config.txt");
            default -> new VacuumState();
        };
    }
}


