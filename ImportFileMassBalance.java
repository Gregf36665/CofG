/**
 * Auto Generated Java Class.
*/ 
import java.io.*;
import java.util.Scanner;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImportFileMassBalance{
  
  // declare class wide variables
  public static double[] x = new double[100]; // array of values imported
  public static String name = MassBalanceImport.aircraftWB(); // Name of file imported
  public static int WBlength = arrayLength(name);
  
  // Import method
  public static double[] selectFile(int value) {    
    x = importData(name);
    double[] output = {x[value*2],x[value*2+1]};
    return output;
    }  
    
  public static double[] importData(String file){
     double[] settings= new double[100];
     int i = 0;
     int toggle = 0;
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader("acWB/" +file)));

            while (s.hasNext()) {
              if(toggle == 0){ WBlength = Integer.parseInt(s.next());
              }
              if(toggle% 3 != 0){
                settings[i] = Double.parseDouble(s.next());
                toggle += 1;
                i++;
              }
              
              else {
                toggle += 1;
                s.next();
              }
              
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found1");
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return settings;
    }
  
    public static String[] importName(String file){
     String[] settings= new String[100];
     int i = 0;
     int toggle = 0;
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader("./acWB/" + file)));

            while (s.hasNext()) {
              if(toggle% 3 == 1){
                settings[i] = (s.next());
                toggle ++;
                i++;;
                
              }
              
              else {
                toggle ++;
                s.next();
              }
              
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found3");
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return settings;
    }
    
  public static int arrayLength(String file){
    System.out.println(file);
    try {
            Scanner s = new Scanner(new BufferedReader(new FileReader("acWB/" +file)));
            {
              int WeightBalanceLength = Integer.parseInt(s.next());
              return WeightBalanceLength;
              }
  }
  catch (FileNotFoundException e) {
            System.out.println("File not found2");
        }
  return 0;
  }
        
}

