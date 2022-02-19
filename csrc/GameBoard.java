/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated.  Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework.  This
 * framework is very effective for turn-based games.  We STRONGLY 
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:  
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with 
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private ConnectFour c4; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 700;
    public static final int BOARD_HEIGHT = 600;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        c4 = new ConnectFour(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for mouseclicks.  Updates the model, then updates the game board
         * based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                
                // updates the model given the coordinates of the mouseclick
                c4.playTurn(p.y / 100, p.x / 100);
                
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        c4.reset();
        status.setText("Player 1's Turn");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    public void undo() {
        c4.undoTurn();
        
        if (c4.getCurrentPlayer()) {
            status.setText("Player 1's Turn");
        } else {
            status.setText("Player 2's Turn");
        }
        
        repaint();
        requestFocusInWindow();

        
    }
    
    public void saveGame(String name) {
        
        try {
            FileWriter a = new FileWriter("" + name + ".txt", false);
            BufferedWriter b = new BufferedWriter(a);
            //PrintWriter c = new PrintWriter(b);
            
            
            int[][] d = c4.getBoard();
            
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d[i].length; j++) {
                    b.write(d[i][j] + 48);
                }
            }
            b.flush();
            b.close();
            
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
        
       

    }
    
public void loadGame(String name) {
        
        LoadGame game = new LoadGame(name);
        
        int[][] newGrid = game.getGrid();
        c4.switchBoard(newGrid);
        
        repaint();
        requestFocusInWindow();
        


    }
    
    

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    
    private void updateStatus() {
        if (c4.getCurrentPlayer()) {
            status.setText("Player 1's Turn");
        } else {
            status.setText("Player 2's Turn");
        }
        
        int winner = c4.checkWinner();
        if (winner == 1) {
            status.setText("Player 1 wins!!!");
        } else if (winner == 2) {
            status.setText("Player 2 wins!!!");
        } else if (winner == 3) {
            status.setText("It's a tie.");
        }
    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board.  This approach
     * will not be sufficient for most games, because it is not 
     * modular.  All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper 
     * methods.  Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draws board grid
        
        
        g.drawLine(100, 0, 100, 600);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(500, 0, 500, 600);
        g.drawLine(600, 0, 600, 600);
        g.drawLine(700, 0, 700, 600);
        
        
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                //g.drawOval(25 + 100 * j, 25 + 100 * i, 50, 50);  
                g.drawOval(10 + 100 * j,10 + 100 * i, 80, 80);  
            }
        }
        
        /*g.drawLine(0, 100, 600, 100);
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 300, 600, 300);
        g.drawLine(0, 400, 600, 400);
        g.drawLine(0, 500, 600, 500);
        g.drawLine(0, 600, 600, 600);
        g.drawLine(0, 700, 600, 700);*/
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int state = c4.getCell(i, j);
                if (state == 1) {
                    g.setColor(Color.RED.darker());
                    g.fillOval(10 + 100 * j, 10 + 100 * i, 80, 80);
                } else if (state == 2) {
                    g.setColor(Color.BLUE.darker());               
                    g.fillOval(10 + 100 * j, 10 + 100 * i, 80, 80);
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}





