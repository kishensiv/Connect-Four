/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework.  This
 * framework is very effective for turn-based games.  We STRONGLY 
 * recommend you review these lecture slides, starting at slide 8, 
 * for more details on Model-View-Controller:  
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard.  The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class Game implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Connect Four");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        // Game board
        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        control_panel.add(reset);
        
        
        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    board.undo();
                }
        });
        control_panel.add(undo);
        
        
        final JButton load = new JButton("Load Game");
        load.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputName = JOptionPane.showInputDialog(frame, "Enter the path for a saved file.", 
                            "Username", JOptionPane.PLAIN_MESSAGE);
                    board.loadGame(inputName);
                }
        });
       
        control_panel.add(load);
        
        final JButton save = new JButton("Save Game");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputName = JOptionPane.showInputDialog(frame, "Enter a name to save this file", 
                        "Username", JOptionPane.PLAIN_MESSAGE);
                board.saveGame(inputName);
            }
        });
        control_panel.add(save);
        
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String INSTRUCTIONS = ("Welcome to Connect Four!\n"
                        + "This is a two player game where players take turns 'dropping' tokens (which are red/blue depending on \nthe player) into the presented columns on the grid."
                        + "The first player to achieve a row of four (in either a \nhorizontal, vertical or diagonal fashion) wins!\n \n"
                        + "In order to play, simply use your mouse cursor to click on the column in which you intend to drop your \ntoken (either as"
                        + " the first player or the second. The panel on the bottom"
                        + "will display which player's turn \nit is at any given moment. You may also undo any given move by simply pressing "
                        + "the undo button. \nLastly, if you wish to save the current progress of any gme, simply press the 'save game' button"
                        + " and \nname your current game. Then, when you wish to resume said game, press the 'load game' button and \nkey in the"
                        + " name of the saved game. Enjoy!");
                JOptionPane.showMessageDialog(frame, INSTRUCTIONS, "Instructions", 
                JOptionPane.PLAIN_MESSAGE);
            }
        });
        control_panel.add(instructions);
        
 
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();
        
        
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}