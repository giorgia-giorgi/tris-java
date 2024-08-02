import java.util.Scanner;

/**
 * This is a Tic-Tac-Toe game where two players take turns marking the spaces in a 3×3 grid.
 * The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game.
 */
public class TicTacToe {
    // 3x3 game board initialized with empty cells represented by "-"
    static String[][] tabella = new String[][]{
            new String[]{"-", "-", "-"},
            new String[]{"-", "-", "-"},
            new String[]{"-", "-", "-"},
    };

    public static void main(String[] args) {
        printWelcomeMessage();

        // Start with player "x"
        String turno = "x";

        stampaTabella();

        System.out.println();

        // Continue the game while the board is not full and there is no winner
        while (!piena() && vincitore() == null) {
            gioca(turno);
            stampaTabella();

            // Switch turns between "x" and "o"
            if (turno.equals("x")) {
                turno = "o";
            } else {
                turno = "x";
            }
        }

        // Determine and display the game result
        String haVinto = vincitore();

        if (haVinto == null) {
            System.out.println("Pareggio!");
        } else {
            System.out.println("Ha vinto " + haVinto);
        }
    }

    /**
     * Prints a styled welcome message to the console.
     */
    public static void printWelcomeMessage() {
        System.out.println("*****************************************");
        System.out.println("*                                       *");
        System.out.println("*      Welcome to Tic-Tac-Toe!          *");
        System.out.println("*                                       *");
        System.out.println("*****************************************");
        System.out.println();
    }

    /**
     * Checks if the game board is full.
     *
     * @return true if the board is full, false otherwise.
     */
    public static boolean piena() {
        for (String[] riga : tabella) {
            for (String segno : riga) {
                if (segno.equals("-")) {
                    return false; // Found an empty cell
                }
            }
        }
        return true; // No empty cells found
    }

    /**
     * Determines the winner of the game.
     *
     * @return "x" or "o" if there is a winner, null otherwise.
     */
    public static String vincitore() {
        // Check rows for a winner
        for (String[] riga : tabella) {
            boolean diverso = false; // Flag to check if all elements in a row are the same
            String primo = riga[0];

            for (String segno : riga) {
                if (primo.equals("-") || !primo.equals(segno)) {
                    diverso = true; // Found a different element or empty cell
                    break;
                }
            }

            if (!diverso) {
                return primo; // Found a winning row
            }
        }

        int numeroRighe = tabella.length;
        int numeroColonne = tabella[0].length;

        // Check columns for a winner
        for (int x = 0; x < 3; x++) {
            boolean diverso = false;
            String primo = tabella[0][x];

            for (int y = 0; y < numeroColonne; y++) {
                String elemento = tabella[y][x];
                if (!elemento.equals(primo) || elemento.equals("-")) {
                    diverso = true; // Found a different element or empty cell
                    break;
                }
            }

            if (!diverso) {
                return primo; // Found a winning column
            }
        }

        // Check main diagonal for a winner
        boolean diverso = false;
        String primo = tabella[0][0];

        for (int indice = 0; indice < numeroRighe; indice++) {
            String elemento = tabella[indice][indice];

            if (!primo.equals(elemento) || elemento.equals("-")) {
                diverso = true; // Found a different element or empty cell
                break;
            }
        }

        if (!diverso) {
            return primo; // Found a winning main diagonal
        }

        // Check anti-diagonal for a winner
        primo = tabella[numeroRighe - 1][0];

        for (int x = 0; x < numeroRighe; x++) {
            int y = 2 - x;
            String elemento = tabella[y][x];
            if (!elemento.equals(primo) || primo.equals("-")) {
                return null; // No winner in the anti-diagonal
            }
        }

        return primo; // Found a winning anti-diagonal
    }

    /**
     * Allows the current player to make a move by specifying the coordinates.
     *
     * @param segno The symbol of the current player ("x" or "o").
     */
    public static void gioca(String segno) {
        int x = 0;
        int y = 0;

        while (true) {
            System.out.println("> " + segno + "'s turn: ");
            Scanner in = new Scanner(System.in);
            x = in.nextInt();
            y = in.nextInt();

            if (tabella[y][x].equals("-"))
                break; // Valid move
        }

        inserisci(x, y, segno);
    }

    /**
     * Prints the current state of the game board.
     */
    public static void stampaTabella() {
        System.out.println();

        System.out.println("> GAME BOARD");

        for (String[] riga : tabella) {
            for (String elemento : riga) {
                System.out.print(elemento);
            }
            System.out.println();
        }
    }

    /**
     * Inserts the player's symbol at the specified coordinates.
     *
     * @param x     The x-coordinate (column) of the move.
     * @param y     The y-coordinate (row) of the move.
     * @param segno The symbol of the player ("x" or "o").
     */
    public static void inserisci(int x, int y, String segno) {
        tabella[y][x] = segno;
    }

    /**
     * Clears the game board, resetting all cells to "-".
     */
    public static void svuotaTabella() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tabella[y][x] = "-";
            }
        }
    }
}
