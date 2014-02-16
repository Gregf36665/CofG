/**
 * Auto Generated Java Class.
*/ 
import java.io.*;
import java.util.Scanner;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MassBalanceImport{
  public static void main(String[] args){
    System.out.println(aircraftWB());
  }
  public static String aircraftWB(){
    // window chooser
    
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("./acWB"));

    chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
      public boolean accept(File f) {
        return f.getName().endsWith(".acWB")
            || f.isDirectory();
      }

      public String getDescription() {
        return "Aircraft Weight and Balance (.acWB)";
      }
    });

    int r = chooser.showOpenDialog(new JFrame());
    if (r == JFileChooser.APPROVE_OPTION) {
    String name = chooser.getSelectedFile().getName();
    return name;
    }
    System.exit(2);
    return "";
  }
}
