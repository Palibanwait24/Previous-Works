
/**
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author AmritPal
 *
 */
public class Main {

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}

/**
 *
 * @author AmritPal
 *
 */
class MyFrame extends JFrame {

    public static final int HEIGHT = 630;
    public static final int WIDTH = 601;

    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        AboutDialog dialog = new AboutDialog(this);
        dialog.setSize(700, 200);
        dialog.setResizable(false);
        dialog.setVisible(true);

        MyPanel panel = new MyPanel();
        add(panel);

    }
}

/**
 * Displays instructions on how to skip
 *
 * @author AmritPal
 *
 */
class AboutDialog extends JDialog {

    public AboutDialog(JFrame owner) {
        super(owner, "Instructions", true);// jFrame, title, model or modeless
        JLabel label1 = new JLabel("        If you have no possible move, "
                + "use the right mouse button to skip your turn!"
                + "      Close the window to start the game!");
        add(label1, BorderLayout.CENTER);

    }
}

/**
 *
 * class games piece holds all game pieces
 *
 */
class GamePieces {

    private Piece[][] pieces = new Piece[8][8];
    Piece piece;

    /**
     * constructor of class takes no parameter and sets the initial center
     * pieces
     */
    public GamePieces() {
        pieces[3][4] = new Piece(Color.BLUE, 3, 4);
        pieces[4][3] = new Piece(Color.BLUE, 4, 3);
        pieces[3][3] = new Piece(Color.RED, 3, 3);
        pieces[4][4] = new Piece(Color.RED, 4, 4);
    }

    /**
     * get pieces returns the 2d array of pieces
     *
     * @return
     */
    public Piece[][] getPieces() {
        return pieces;
    }

    /**
     * adds a piece to the piece array
     *
     * @param playerColor -- takes the color as a parameter and uses it to set
     * the color of the piece
     * @param x -- takes in the row as a parameter for the piece
     * @param y - takes in the column of the new piece
     */
    public void addPiece(Color playerColor, int x, int y) { // add a circle
        // piece to the 2d
        // array
        pieces[x][y] = new Piece(playerColor, x, y); // creates a new piece ate the row and the column

    }
}

/**
 *
 * class myPanel sets the panel
 *
 */
class MyPanel extends JPanel {

    private GameBoard board = new GameBoard();
    private GamePieces array = new GamePieces();
    private Piece[][] pieces = array.getPieces(); // circle pieces
    private Rectangle2D[][] boardPiece = board.getGrid(); // rectangle grid

    private int mouseX; // location of the cursor
    private int mouseY;// location of the cursor
    private int mouseClickX;// location of the mouse click
    private int mouseClickY; // location of the mouse click
    private boolean piece1 = false;
    private String s = "";
    private boolean up = false; // check up
    private boolean down = false; // check down
    private boolean left = false; // check left
    private boolean right = false; // check right
    private boolean dRUp = false; // Diagonal right and up
    private boolean dRDown = false; // Diagonal right and down
    private boolean dLUp = false; // Diagonal left and up
    private boolean dLDown = false; // Diagonal left and down

    private Player player1; // creates player 1
    private Player computer; // creates computer player
    private boolean assignPlayer = true;
    private Player currentPlayer; // current player assigned to player1
    private Player oppositePlayer;// opposite player assigned to computer

    /**
     * my panel class that handles everything with the panel
     */
    public MyPanel() {
        // randomly assigns players only once. once players have been assigned set assignPlayer to false
        if (assignPlayer == true) {
            assignPlayer = false;
            int random = (int) (Math.random() * 2);
            if (random == 1) {
                player1 = new Player(Color.BLUE);
                computer = new Player(Color.RED);
                currentPlayer = player1;
                oppositePlayer = computer;
            } else {
                player1 = new Player(Color.RED);
                computer = new Player(Color.BLUE);
                currentPlayer = computer;
                oppositePlayer = player1;
            }
        }

        addMouseMotionListener(new MouseMotionAdapter() { // adds motion listener to panel

            public void mouseMoved(MouseEvent e) {
                int cp = 0;
                int hp = 0;
                int all = 0;
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        if (pieces[x][y] != null) {
                            if (pieces[x][y].getColor() == computer.getColor()) {
                                ;
                                cp++;
                                all++;
                            } else {
                                hp++;
                                all++;
                            }
                        }
                        if (all > 63) {
                            if (cp > hp) {
                                s = "*** COMPUTER WINS ***";
                            } else if (cp < hp) {
                                s = "*** YOU WIN ***";
                            } else {
                                s = "*** YOU TIE ***";
                            }
                        }
                    }
                }
                int column = 0; // initializes column to 0
                int row = 0; // initializes row to 0

                if (currentPlayer == player1) { // if player 1 turn then get the location of the mouse

                    column = e.getX() / 75;
                    row = e.getY() / 75;
                } else if (currentPlayer == computer) { // if computer turn get the ai

                    int[] p = ai(); // get good move for ai 
                    if (p == null) {
                        currentPlayer = player1;
                        oppositePlayer = computer;
                        repaint();
                    } else {

                        row = p[0];
                        column = p[1];
                    }

                }
                if (currentPlayer == player1) { // if player 1 turn check the move with mouse location
                    if (column < 8 && row < 8) {
                        checkMove(column, row);
                    }
                } else { // if computer turn check with ai location and add piece

                    checkMove(column, row);
                    array.addPiece(computer.getColor(), row, column);
                    repaint();

                }

                repaint();

            }
        });

        addMouseListener(new MouseAdapter() { // mouse clicked
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 3) {
                    repaint();
                    currentPlayer = computer;
                    oppositePlayer = player1;
                    repaint();
                }
                if (e.getButton() == 1) { // if left mouse button pressed
                    if (up == true || down == true || left == true || right == true
                            || dRUp == true || dRDown == true || dLUp == true || dLDown == true) {
                        // if check up is true or check down is true
                        int row = mouseY; // column = x pixel location divided by 75 pixels
                        int column = mouseX; // row = y pixel location divided by 75 pixels

                        mouseClickY = row; // global variable mouseclickX = the column 
                        mouseClickX = column; // golobal mouse clickY = the row

                        piece1 = true; // piece1 set to true so does not paint piece to 0,0
                        changePieces(mouseClickY, mouseClickX); // calls changePieces with row and column

                    }
                }
            }
        });
        repaint();

    }

    /**
     * changes pieces between setpiece and same pieces
     *
     * @param row - takes in the row of the piece
     * @param column - takes in the column of the piece
     */
    public void changePieces(int row, int column) { // changes pieces between setpiece and same pieces
        if (pieces[row][column] == null) {

            if (down == true) {// check down

                for (int r = row + 1; r < 8; r++) {
                    if (pieces[r][column] == null) {    // if piece is null break out of loop
                        break;
                    }
                    if (pieces[r][column].getColor() == currentPlayer.getColor()) { // if piece is the same color
                        // as the player color
                        break; // break out of loop
                    } else {
                        pieces[r][column].setNewColor(currentPlayer.getColor()); // sets piece colors 

                    }
                }
            }

            if (dRUp == true) {// check diagonal up and to the right

                int c = column + 1;
                for (int r = row - 1; r > 0; r--) {
                    if (pieces[r][c] == null) {
                        break;
                    }
                    if (pieces[r][c] != null) {
                        if (pieces[r][c].getColor() == currentPlayer
                                .getColor()) {
                            break;
                        } else {
                            pieces[r][c]
                                    .setNewColor(currentPlayer
                                            .getColor());

                        }
                    }
                    c++;
                }
            }

            if (dRDown == true) {// check diagonal down and to the right

                int c = column + 1;
                for (int r = row + 1; r < 8; r++) {
                    if (pieces[r][c] == null) {
                        break;
                    }
                    if (pieces[r][c] != null) {
                        if (pieces[r][c].getColor() == currentPlayer
                                .getColor()) {
                            break;
                        } else {
                            pieces[r][c]
                                    .setNewColor(currentPlayer
                                            .getColor());

                        }
                    }
                    c++;
                }
            }

            if (right == true) {// check right

                for (int c = column + 1; c < 8; c++) {
                    if (pieces[row][c] == null) {
                        break;
                    }
                    if (pieces[row][c].getColor() == currentPlayer
                            .getColor()) {

                        break;
                    } else {
                        pieces[row][c]
                                .setNewColor(currentPlayer
                                        .getColor());

                    }
                }
            }

            if (up == true) {// check up

                for (int r = row - 1; r > 0; r--) {
                    if (pieces[r][column] == null) {
                        break;
                    }
                    if (pieces[r][column].getColor() == currentPlayer
                            .getColor()) {

                        break;
                    } else {
                        pieces[r][column]
                                .setNewColor(currentPlayer
                                        .getColor());

                    }
                }
            }

            if (left == true) {// check left

                for (int c = column - 1; c > 0; c--) {
                    if (pieces[row][c] == null) {
                        break;
                    }
                    if (pieces[row][c].getColor() == currentPlayer
                            .getColor()) {

                        break;
                    } else {
                        pieces[row][c]
                                .setNewColor(currentPlayer
                                        .getColor());

                    }
                }
            }

            if (dLUp == true) {// check diagonal up and to the left

                int c = column - 1;
                for (int r = row - 1; r > 0; r--) {
                    if (pieces[r][c] == null) {
                        break;
                    }
                    if (pieces[r][c] != null) {
                        if (pieces[r][c].getColor() == currentPlayer
                                .getColor()) {
                            break;
                        } else {
                            pieces[r][c]
                                    .setNewColor(currentPlayer
                                            .getColor());

                        }
                    }

                }
            }

            if (dRDown == true) {// check diagonal down and to the right

                int c = column + 1;
                for (int r = row + 1; r < 8; r++) {
                    if (pieces[r][c] == null) {
                        break;
                    }
                    if (pieces[r][c] != null) {
                        if (pieces[r][c].getColor() == currentPlayer
                                .getColor()) {
                            break;
                        } else {
                            pieces[r][c]
                                    .setNewColor(currentPlayer
                                            .getColor());

                        }
                    }
                    c++;
                }
            }

            if (dLDown == true) {// check diagonal down and to the left

                int c = column - 1;
                for (int r = row + 1; r < 8; r++) {
                    if (pieces[r][c] == null) {
                        break;
                    }
                    if (pieces[r][c] != null) {
                        if (pieces[r][c].getColor() == currentPlayer
                                .getColor()) {
                            break;
                        } else {
                            pieces[r][c]
                                    .setNewColor(currentPlayer
                                            .getColor());

                        }
                    }
                    c--;
                }
            }

            repaint();
            if (currentPlayer == player1) { //switches players
                oppositePlayer = player1;
                currentPlayer = computer;
            } else {
                oppositePlayer = computer;
                currentPlayer = player1;
            }

        }
    }

    public void checkMove(int column, int row) {

        up = false; // resets check booleans to false
        down = false;// resets check booleans to false
        left = false;// resets check booleans to false
        right = false;// resets check booleans to false
        dRUp = false;// resets check booleans to false
        dRDown = false;// resets check booleans to false
        dLUp = false;// resets check booleans to false
        dLDown = false;// resets check booleans to false

        if (pieces[row][column] == null) {
            mouseY = row;
            mouseX = column;

            if (row < 7) {
                if (pieces[row + 1][column] != null) {
                    if (pieces[row + 1][column].getColor() == oppositePlayer.getColor()) {// check down

                        for (int r = row + 1; r < 7; r++) {
                            if (pieces[r][column] == null) {
                                break;
                            }
                            if (pieces[r][column].getColor() == currentPlayer
                                    .getColor()) {
                                down = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (column < 7) {
                if (pieces[row][column + 1] != null) { // check
                    // right
                    if (pieces[row][column + 1].getColor() == oppositePlayer
                            .getColor()) {
                        for (int c = column + 1; c < 8; c++) {
                            if (pieces[row][c] == null) {
                                break;
                            }
                            if (pieces[row][c].getColor() == currentPlayer
                                    .getColor()) {
                                right = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (row > 0 && column < 7) {
                if (pieces[row - 1][column + 1] != null) {// check // diagonal
                    // up and to the
                    // right
                    if (pieces[row - 1][column + 1].getColor() == oppositePlayer
                            .getColor()) {
                        int c = column + 1;
                        for (int r = row - 1; r > 0; r--) {
                            if (c < 8) {
                                if (pieces[r][c] == null) {
                                    break;
                                }
                                if (pieces[r][c] != null) {
                                    if (pieces[r][c].getColor() == currentPlayer
                                            .getColor()) {
                                        dRUp = true;
                                        break;
                                    }
                                    c++;
                                }
                            }
                        }
                    }
                }
            }

            if (row < 7 && column < 7) {
                if (pieces[row + 1][column + 1] != null) {// check diagonal down
                    // and to the right
                    if (pieces[row + 1][column + 1].getColor() == oppositePlayer.getColor()) {
                        int c = column + 1;
                        for (int r = row + 1; r < 8; r++) {
                            if (c < 8) {
                                if (pieces[r][c] == null) {
                                    break;
                                }
                                if (pieces[r][c] != null) {
                                    if (pieces[r][c].getColor() == currentPlayer
                                            .getColor()) {

                                        dRDown = true;
                                        break;
                                    }
                                    c++;
                                }
                            }
                        }
                    }
                }
            }

            if (row > 0) {

                if (pieces[row - 1][column] != null) {// check up

                    if (pieces[row - 1][column].getColor() == oppositePlayer.getColor()) {

                        for (int r = row - 1; r > 0; r--) {
                            if (pieces[r][column] == null) {

                                break;
                            }
                            if (pieces[r][column].getColor() == currentPlayer.getColor()) {

                                up = true;
                                break;
                            }

                        }
                    }
                }
            }

            if (column > 0) {
                if (pieces[row][column - 1] != null) {
                    if (pieces[row][column - 1].getColor() == oppositePlayer
                            .getColor()) { // check left
                        for (int c = column - 1; c > 0; c--) {
                            if (pieces[row][c] == null) {
                                break;
                            } else if (pieces[row][c].getColor() == currentPlayer.getColor()) {
                                left = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (row > 0 && column > 0) {
                if (pieces[row - 1][column - 1] != null) {// check
                    // diagonal
                    // up and to
                    // the left
                    if (pieces[row - 1][column - 1].getColor() == oppositePlayer.getColor()) {
                        int c = column - 1;
                        for (int r = row - 1; r > 0; r--) {
                            if (c > 0) {
                                if (pieces[r][c] == null) {
                                    break;
                                }
                                if (pieces[r][c] != null) {
                                    if (pieces[r][c].getColor() == currentPlayer
                                            .getColor()) {
                                        dLUp = true;
                                        break;
                                    }
                                    c--;
                                }
                            }

                        }
                    }
                }
            }
            if (row < 7 && column > 0) {
                if (pieces[row + 1][column - 1] != null) {// check
                    // diagonal
                    // down
                    // and
                    // to
                    // the
                    // left
                    if (pieces[row + 1][column - 1].getColor() == oppositePlayer
                            .getColor()) {
                        int c = column - 1;
                        for (int r = row + 1; r < 8; r++) {
                            if (c > 0) {
                                if (pieces[r][c] == null) {
                                    break;
                                }
                                if (pieces[r][c] != null) {
                                    if (pieces[r][c].getColor() == currentPlayer
                                            .getColor()) {
                                        dLDown = true;
                                        break;
                                    }
                                    c--;
                                }
                            }
                        }
                    }
                }
            }
            if (currentPlayer == computer) { // if computer turn then change piece colors
                changePieces(row, column);
            }
            repaint(); // repaints screen
        }
    }

    /**
     * fills background of square to green if valid move
     *
     * @param g2 takes in the 2d brush
     */

    public void isVaildMove(Graphics2D g2) {
        if (up == true || down == true || left == true || right == true || dRUp == true
                || dRDown == true || dLUp == true || dLDown == true) { // if valid move

            board.fill(g2, mouseY, mouseX); // fill the square green

            repaint();
        }

    }

    /**
     * adds piece to array if location is valid move
     */
    public void addPiece() { // add circle piece
        if (piece1) { // piece is true -- need because panel would set piece 0,0 with a piece
            if (up == true || down == true || left == true || right == true
                    || dRUp == true || dRDown == true || dLUp == true
                    || dLDown == true) {

                array.addPiece(currentPlayer.getColor(), mouseClickY,
                        mouseClickX); // add piece to the piece array
            }
        }
    }

    /**
     * ai method -- if corners are empty place there first
     *
     * @return valid location or null where a piece can be placed
     */
    public int[] ai() {
        boolean aiValidMove = false;
        int[] x = new int[2];
        int z = 0;
        int u = 0;

        for (int i = 0; i < 2; i++) { // checks corners of the board for move
            for (int q = 0; q < 2; q++) {
                int row = z;
                int column = u;
                aiValidMove = aiCheck(row, column);
                if (aiValidMove == true) { // if ai is valid move is true return x array
                    x[0] = row;
                    x[1] = column;
                    return x;
                }
                u = 7;
            }

            z = 7;

        }

        int count = 0;
        count++;
        for (int i = 0; i < 8; i++) { // checks corners of the board for move
            for (int q = 0; q < 8; q++) {
                z = (int) (Math.random() * 8);
                u = (int) (Math.random() * 8);

                aiValidMove = aiCheck(z, u);
                if (aiValidMove == true) { // if ai is valid move is true return x array
                    x[0] = z;
                    x[1] = u;
                    return x;

                }

            }

        }
        return null;

    }

    /**
     * checks if the ai move is valid
     *
     * @param row - takes in the row
     * @param column -- takes in the column
     * @return aiValidMove - if the move is valid return true
     */
    public boolean aiCheck(int row, int column) {
        boolean aiValidMove = false;

        if (pieces[row][column] == null) {
            if (row < 7) {
                if (pieces[row + 1][column] != null) {
                    if (pieces[row + 1][column].getColor() == oppositePlayer
                            .getColor()) {// check down
                        for (int r = row + 1; r < 8; r++) {
                            if (pieces[r][column] == null) {
                                break;
                            }
                            if (pieces[r][column].getColor() == currentPlayer
                                    .getColor()) {
                                aiValidMove = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (column < 7) {
                if (pieces[row][column + 1] != null) { // check right
                    if (pieces[row][column + 1].getColor() == oppositePlayer.getColor()) {
                        for (int c = column + 1; c < 8; c++) {
                            if (pieces[row][c] == null) {
                                break;
                            }
                            if (pieces[row][c].getColor() == currentPlayer.getColor()) {
                                aiValidMove = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (row > 0 && column < 7) {
                if (pieces[row - 1][column + 1] != null) {// check diagonal
                    // up and to the
                    // right
                    if (pieces[row - 1][column + 1].getColor() == oppositePlayer.getColor()) {
                        int c = column + 1;
                        for (int r = row - 1; r > 0; r--) {
                            if (c < 8) {
                                if (pieces[r][c] == null) {
                                    break;
                                }
                                if (pieces[r][c] != null) {
                                    if (pieces[r][c].getColor() == currentPlayer.getColor()) {
                                        aiValidMove = true;
                                        break;
                                    }
                                    c++;
                                }
                            }
                        }
                    }
                }
            }
            if (row < 7 && column < 7) {
                if (pieces[row + 1][column + 1] != null) {// check diagonal down
                    // and to the right
                    if (pieces[row + 1][column + 1].getColor() == oppositePlayer
                            .getColor()) {
                        int c = column + 1;
                        for (int r = row + 1; r < 8; r++) {
                            if (c < 8) {
                                if (pieces[r][c] != null) {
                                    if (pieces[r][c].getColor() == currentPlayer.getColor()) {
                                        aiValidMove = true;
                                        break;
                                    }
                                    c++;
                                }
                            }
                        }
                    }
                }
            }
            if (row > 0) {

                if (pieces[row - 1][column] != null) {// check up

                    if (pieces[row - 1][column].getColor() == oppositePlayer.getColor()) {

                        for (int r = row - 1; r > 0; r--) {
                            if (pieces[r][column] == null) {
                                break;
                            }
                            if (pieces[r][column].getColor() == currentPlayer
                                    .getColor()) {
                                aiValidMove = true;
                                break;
                            }

                        }
                    }
                }
            }
            if (column > 0) {
                if (pieces[row][column - 1] != null) {
                    if (pieces[row][column - 1].getColor() == oppositePlayer
                            .getColor()) { // check left
                        for (int c = column - 1; c > 0; c--) {
                            if (pieces[row][c] == null) {
                                break;
                            }
                            if (pieces[row][c].getColor() == currentPlayer
                                    .getColor()) {
                                aiValidMove = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (row > 0 && column > 0) {
                if (pieces[row - 1][column - 1] != null) {// check
                    // diagonal
                    // up and to
                    // the left
                    if (pieces[row - 1][column - 1].getColor() == oppositePlayer
                            .getColor()) {
                        int c = column - 1;
                        for (int r = row - 1; r > 0; r--) {
                            if (c >= 0) {
                                if (pieces[r][c] == null) {
                                    break;
                                }
                                if (pieces[r][c] != null) {
                                    if (pieces[r][c].getColor() == currentPlayer.getColor()) {
                                        aiValidMove = true;
                                        break;
                                    }
                                    c--;
                                }
                            }
                        }
                    }
                }
            }
            if (row < 7 && column > 0) {
                if (pieces[row + 1][column - 1] != null) {// check
                    // diagonal
                    // down
                    // and
                    // to
                    // the
                    // left
                    if (pieces[row + 1][column - 1].getColor() == oppositePlayer
                            .getColor()) {
                        int c = column - 1;
                        for (int r = row + 1; r < 7; r++) {
                            if (c >= 0) {
                                if (pieces[r][c] == null) {
                                    break;
                                }
                                if (pieces[r][c] != null) {
                                    if (pieces[r][c].getColor() == currentPlayer
                                            .getColor()) {
                                        aiValidMove = true;
                                        break;
                                    }
                                    c--;
                                }
                            }
                        }
                    }
                }
            }
        }
        return aiValidMove;
    }

    /**
     * paint component with graphics g brush
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.BLACK); // sets background color to black
        board.draw(g2); // draws the square board 

        isVaildMove(g2); // calls valid move with g2 parameter
        addPiece(); // adds piece

        // draws pieces to panel
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces[x][y] != null) {
                    pieces[x][y].DrawPiece(g2);
                }

            }
        }
        if (s != "") {
            g2.setColor(Color.BLACK);
            g2.fill(new Rectangle2D.Double(0, 0, 600, 600));
            g2.setColor(Color.WHITE);
            Font myFont = new Font("SansSerif", Font.BOLD, 40);
            g2.setFont(myFont);
            g2.drawString(s, 100, 100);
        }
    }

}
