
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LoadGame {
    
    private int[][] board;
    
    public LoadGame(String name) {
        
        board = new int[6][7];
        
        try {
            //FileReader a = new FileReader("" + name + ".txt");
            FileReader a = new FileReader("" + name + ".txt");
            BufferedReader b = new BufferedReader(a);
            
            
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = b.read() - 48;
                }
            }
            b.close();
            
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
       
    }
    
    public int[][] getGrid() {
        return board;
    }
}
