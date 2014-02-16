/**Dimention values for aircraft
  * These are the dimentions for the 10ft foam cargo RC plane
  * This program will be able to read in a file and load the data in
  **/
import java.io.*;
import java.util.Scanner;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class GlobalStorage {
  
    /*************************/
  /***DISPLAY*SETTINGS*****/
  /***********************/
  
  
  // origin of the nose
  public static int nosePosX = 1000; // x nose position
  public static int nosePosY = 300; // y nose position
  
  // scale for inch to pixel
  
  public static int scale = 8;
  
  /**********END************/
  /***DISPLAY*SETTINGS*****/
  /***********************/
  
  
// Airplane design data
  public static String aircraftFileName = aircraftFileName();
    
  public static String aircraftFileName(){
    // window chooser
    
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("./acDim"));
    chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
      public boolean accept(File f) {
        return f.getName().endsWith(".acDim")
          || f.isDirectory();
      }
      
      public String getDescription() {
        return "Aircraft Dimentions (.acDim)";
      }
    });
    
    int r = chooser.showOpenDialog(new JFrame());
    if (r == JFileChooser.APPROVE_OPTION) {
      String name = chooser.getSelectedFile().getName();
      return name;
    }
    else{
      String name = Main.aircraftName[1];
      return name;
    }
  }
  
  
  
  /*******************************/
  /*********Plane*Specific*******/
  /*****************************/
  
  // declare the dimention variables in inches
  // nose
  public static int radNoseV = ImportFile.selectFile(0); //radius of the nose vertical
  public static int radNoseH = ImportFile.selectFile(1); //radius of the nose horizontal
  
  // body
  public static int fuselageLength = ImportFile.selectFile(2); //length from nose to tail
  public static int fuselageHeight = ImportFile.selectFile(3); // height of main area
  public static int epionageLength = ImportFile.selectFile(4); // length of epionage
  public static int epionageHeight = ImportFile.selectFile(5); // height of aft epionage
  public static int blockHeight = fuselageHeight-(radNoseV*2); //height of cockpit
  public static int blockLength = ImportFile.selectFile(6); //distance from start of block till end horizontally
  
  // flying surfaces
  
  // vertical surfaces
  public static int tailPlaneHeight = ImportFile.selectFile(7); // height of tail plane 
  public static int tailPlaneBaseLength = ImportFile.selectFile(8); // length of tailplane base
  public static int tailPlaneTopLength = ImportFile.selectFile(9); // length of the top of the tail plane
  public static int tailPlaneDifference = ImportFile.selectFile(10); // difference between edge of base and edge of top of tail plane
  
  // horizontal surfaces
  public static int mainWingPlaneHeight = ImportFile.selectFile(11); // semi-minor axis of the main wing  (diameter)
  public static int mainWingPlaneLength = ImportFile.selectFile(12); // semi-major axis of the main wing (diameter)
  public static int mainWingPosH = ImportFile.selectFile(13); // distance from nose to the leading edge of the main wing
  public static int mainWingPosV = ImportFile.selectFile(14); // distance from the base of the fuselage to the base of the wing
  public static int tailWingPlaneHeight = ImportFile.selectFile(15); // semi-minor axis of the horizontal stabilizer 
  public static int tailWingPlaneLength = ImportFile.selectFile(16); // semi-major axis of the horizontal stabilizer
  
  /***************************/
  /************END***********/
  /*************************/
  
  

  
  
  
  /**********DO*NOT*ADJUST*BENEATH*LINE*********/
  /********************************************/
  
  // scale everything up
  
  public static int radNoseVS = radNoseV*scale; //radius of the nose vertical
  public static int radNoseHS = radNoseH*scale; //radius of the nose horizontal
  public static int fuselageLengthS = fuselageLength*scale; //length from nose to tail
  public static int fuselageHeightS = fuselageHeight*scale; // height of main area
  public static int epionageLengthS = epionageLength*scale; // length of epionage
  public static int epionageHeightS = epionageHeight*scale; // height of aft epionage
  public static int tailPlaneHeightS = tailPlaneHeight*scale; // height of tail plane 
  public static int tailPlaneBaseLengthS = tailPlaneBaseLength*scale; // length of tailplane base
  public static int tailPlaneTopLengthS = tailPlaneTopLength*scale; // length of the top of the tail plane
  public static int tailPlaneDifferenceS = tailPlaneDifference*scale; // difference between edge of base and edge of top of tail plane
  public static int blockHeightS = blockHeight*scale; //height of cockpit
  public static int blockLengthS = blockLength*scale; //distance from start of block till end horizontally
  public static int mainWingPlaneHeightS = mainWingPlaneHeight*scale; // semi-minor axis of the main wing 
  public static int mainWingPlaneLengthS = mainWingPlaneLength*scale; // semi-major axis of the main wing
  public static int tailWingPlaneHeightS = tailWingPlaneHeight*scale; // semi-minor axis of the horizontal stabilizer 
  public static int tailWingPlaneLengthS = tailWingPlaneLength*scale; // semi-major axis of the horizontal stabilizer
  public static int mainWingPosHS = mainWingPosH*scale; // distance from nose to the leading edge of the main wing
  public static int mainWingPosVS = mainWingPosV*scale; // distance from the base of the fuselage to the base of the wing
}
