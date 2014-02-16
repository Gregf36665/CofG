/**
 * Auto Generated Java Class.
 */
import java.io.*;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;

import javax.swing.*;


import java.util.Scanner;

public class ExportAircraft {
  
  //class wide variables
  public static String aircraftName = "";
  public static int[] data = new int[17];
  public static boolean overwrite = false;
  public final static boolean RIGHT_TO_LEFT = false;
  
  public static void main(String args[]) {
    if(args.length == 1){
      aircraftName = args[0];
    }
    else{
    aircraftName = (String)JOptionPane.showInputDialog(null, "Aircraft name:");
    
    if (aircraftName == null){
      System.exit(1); // if the cancel button is pressed
    }
    aircraftName = aircraftName.replaceAll("\\s",""); //remove any spaces
    File f = new File("./acDim/"+aircraftName + ".acDim");
    if(f.exists() && !f.isDirectory()) { // check if it exists
    
      JOptionPane pane = new JOptionPane("File already exists\nEdit?");
      Object[] options = new String[] { "No", "Yes" };
      pane.setOptions(options);
      JDialog dialog = pane.createDialog(new JFrame(), "File conflict");
      dialog.setVisible(true);
      Object obj = pane.getValue(); 
      if(obj=="Yes"){ // abort if asked
      overwrite = true;
      data = importData(aircraftName+".acDim");
      System.out.println(data[0]);
    System.out.println("Making window");
    defaultWindow();
      }
      else{// abort if asked
        dialog.setVisible(false);
      }
    }
      if(!f.exists()){
        System.out.println("Making window");
        defaultWindow();
      }
    }
  }

  public static void newFile(){
    // build the text output
    String[] output = new String[17];
    String[] options = { "radiusNoseVertical", "radiusNoseHorizontal","fuselageLength",
          "fuselageHeight", "epionageLength", "epionageHeight", "blockLength", "tailPlaneHeight", "tailPlaneWidth", 
        "tailPlaneTopLength", "tailPlaneDifference", "mainWingPlaneHeight", "mainWingPlaneLength", "mainWingPosH",
        "mainWingPosV", "tailWingPlaneHeight", "tailWingPlaneLength"};

    for(int i=0;i<17;i++){
      output[i] = (options[i] + ": " + data[i] + "\n"); 
    }
    StringBuilder builder = new StringBuilder();
    for (String value : output) {
      builder.append(value);
    }
    String textOut = builder.toString();
    try{
      // Create file
      String extension = ".acDim";
      FileWriter fstream = new FileWriter("acDim/" + aircraftName + extension);
      BufferedWriter out = new BufferedWriter(fstream);
      out.write(textOut);
      //Close the output stream
      out.close();
    }
    catch (Exception e){//Catch exception if any
      System.err.println("Error: " + e.getMessage());
    }
  }
  
  public static void defaultWindow(){

    final JFrame frame = new JFrame("Settings");
    JButton button1 = new JButton("Edit settings");
    JButton button2 = new JButton("Export file");
    
    final String[] options = new String[20]; // this is to ensure that there is a scroll box rather than a dropdown menu
    String[] choice = {"radiusNoseVertical", "radiusNoseHorizontal","fuselageLength",
          "fuselageHeight", "epionageLength", "epionageHeight", "blockLength", "tailPlaneHeight", "tailPlaneWidth", 
        "tailPlaneTopLength", "tailPlaneDifference", "mainWingPlaneHeight", "mainWingPlaneLength", "mainWingPosH",
          "mainWingPosV", "tailWingPlaneHeight", "tailWingPlaneLength"};
    
    for(int i=0;i<17;i++){
      options[i] = choice[i];
    }
    
    ActionListener actionListener1 = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        Component source = (Component) actionEvent.getSource();
        Object response = JOptionPane.showInputDialog(source,
            "Select dimention to edit",
            "Select a Destination", JOptionPane.QUESTION_MESSAGE,
            null, options ,"radiusNoseVertical");
        String reply =  (String) response;
        
        // Find which variable to change
        if(response == "radiusNoseVertical"){
          data[0] = changeVariable("radiusNoseVertical",0);
        }
        if(response == "radiusNoseHorizontal"){
          data[1] = changeVariable("radiusNoseHorizontal",1);
        }
        if(response == "fuselageLength"){
          data[2] = changeVariable("fuselageLength",2);
        }
        if(response == "fuselageHeight"){
          data[3] = changeVariable("fuselageHeight",3);
        }
        if(response == "epionageLength"){
          data[4] = changeVariable("epionageLength",4);
        }
        if(response == "epionageHeight"){
          data[5] = changeVariable("epionageHeight",5);
        }
        if(response == "blockLength"){
          data[6] = changeVariable("blockLength",6);
        }
        if(response == "tailPlaneHeight"){
          data[7] = changeVariable("tailPlaneHeight",7);
        }
        if(response == "tailPlaneWidth"){
          data[8] = changeVariable("tailPlaneWidth",8);
        }
        if(response == "tailPlaneTopLength"){
          data[9] = changeVariable("tailPlaneTopLength",9);
        }
        if(response == "tailPlaneDifference"){
          data[10] = changeVariable("tailPlaneDifference",10);
        }
        if(response == "mainWingPlaneHeight"){
          data[11] = changeVariable("mainWingPlaneHeight",11);
        }
        if(response == "mainWingPlaneLength"){
          data[12] = changeVariable("mainWingPlaneLength",12);
        }
        if(response == "mainWingPosH"){
          data[13] = changeVariable("mainWingPosH",13);
        }
        if(response == "mainWingPosV"){
          data[14] = changeVariable("mainWingPosV",14);
        }
        if(response == "tailWingPlaneHeight"){
          data[15] = changeVariable("tailWingPlaneHeight",15);
        }
        if(response == "tailWingPlaneLength"){
          data[16] = changeVariable("tailWingPlaneLength",16);
        }
      }
    };
    
      ActionListener actionListener2 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
          newFile();
          System.out.println("Exporting");
          newFile();
          JOptionPane pane = new JOptionPane("Exported");
          Object[] options = new String[] { "OK" };
          pane.setOptions(options);
          JDialog dialog = pane.createDialog(new JFrame(), "File conflict");
          dialog.setVisible(true);
          frame.setVisible(false);
          //Main.main(test);
          
        }
    };
      
    button1.addActionListener(actionListener1);
    button2.addActionListener(actionListener2);
    Container contentPane = frame.getContentPane();
    contentPane.add(button1, BorderLayout.PAGE_START);
    contentPane.add(button2, BorderLayout.PAGE_END);
    frame.setSize(300, 100);
    frame.setLocation(500,330);
    frame.setVisible(true);
  }
  
  
  // import predefined variables
  public static int[] importData(String file){
     int[] settings= new int[17];
     int i = 0;
     boolean toggle = false;
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader("acDim/" + file)));

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
            System.out.println("File not found");
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return settings;
    }
  
  
  
  // changing variables
  public static int changeVariable(String var,int caseNum){
    int defaultValue = 0; 
    
    if(overwrite){defaultValue = data[caseNum];}
    
    String value = JOptionPane.showInputDialog(null, "New value of \"" + var +"\":", defaultValue);
    if (value == null){return 10;}
    int output = Integer.parseInt(value);
    return output;
}
}