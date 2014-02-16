/**
 * Auto Generated Java Class.
 */
import java.io.*;
import java.util.Scanner;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImportFile{
  
  // declare class wide variables
  static int[] x = new int[17]; // array of values imported
  static String name = GlobalStorage.aircraftFileName; // Name of file imported
  
  // Import method
  public static int selectFile(int value) {    
    x = importData("acDim/"+name);
    return x[value];
    }  
    
  public static int[] importData(String file){
     int[] settings= new int[17];
     int i = 0;
     boolean toggle = false;
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(file)));

            while (s.hasNext()) {
              if(toggle){
                settings[i] = (int) Double.parseDouble(s.next());
                toggle = false;
                i++;
              }
              
              else {
                toggle = true;
                s.next();
              }
              
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found0");
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return settings;
    }
}
