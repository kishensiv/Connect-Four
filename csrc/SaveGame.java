import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveGame {
    
    public SaveGame(String name, ConnectFour game) {
        
        try {
            FileWriter a = new FileWriter("" + name + ".txt", true);
            BufferedWriter b = new BufferedWriter(a);
           
            //PrintWriter c = new PrintWriter(b);
            
            a.write(1);
            
            int[][] d = game.getBoard();
            
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d[i].length; j++) {
                    b.write(1); 
                    b.newLine();
                    //b.write(d[i][j]);
                }
            }
            
            b.flush();
            b.close();
            
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        
    }
}


/*

public class ReadFile implements Iterator<String> {

    private BufferedReader r;
    private String line;


public ReadFile(String filePath) {
        try {
            
            r = new BufferedReader(new FileReader(filePath));
            line =  r.readLine();
                
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        } catch (IOException e) {
            line = null;
        }

    }

}

*/


















































