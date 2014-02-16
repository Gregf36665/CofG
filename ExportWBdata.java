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

public class ExportWBdata extends JFrame {
  
  // Global variables
  static double[] output = new double[200];
  static String[] name = new String[100];
  static int count = 0;
  static String aircraftName = "";
  static boolean overwrite = false;
  static int momentCount = 0;
  
  // Build the window
  public static void defaultWindow(){
    aircraftName = (String)JOptionPane.showInputDialog(null, "File name:");
    
    if (aircraftName == null){
      System.exit(1); // if the cancel button is pressed
    }
    
    aircraftName = aircraftName.replaceAll("\\s",""); //remove any spaces
    File f = new File("./acWB/"+aircraftName + ".acWB");
    if(f.exists() && !f.isDirectory()) { // check if it exists
    
      JOptionPane pane = new JOptionPane("File already exists\nEdit?");
      Object[] options = new String[] { "No", "Yes" };
      pane.setOptions(options);
      JDialog dialog = pane.createDialog(new JFrame(), "File conflict");
      dialog.setVisible(true);
      Object obj = pane.getValue(); 
      if(obj=="No"){ 
        System.exit(1); // abort if asked
      } 
      overwrite = true;
      // import the data
      count = ImportFileMassBalance.WBlength*2;
      momentCount = count/2;
      for(int i =0; i<count; i++){
      output[i*2] = ImportFileMassBalance.selectFile(i)[0];
      output[i*2+1] = ImportFileMassBalance.selectFile(i)[1];
    }
      // import the dimention names
      
      for(int i=0; i<=momentCount; i++){
        name[i] = ImportFileMassBalance.importName(aircraftName + ".acWB")[i];
      }
    }
      
    final JFrame frame = new JFrame("Settings");
    JButton button1 = new JButton("Add moment"); // later on add in a delete moment command
    JButton button4 = new JButton("Delete moment");
    JButton button3 = new JButton("Export");
    JButton button2 = new JButton("Show all moments"); //open the text file
    
    // Add a moment arm in
    
    ActionListener actionListener1 = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        Component source = (Component) actionEvent.getSource();
        name[momentCount] = (JOptionPane.showInputDialog(source, "Enter the name")+":");
        String response = JOptionPane.showInputDialog(source, "Enter the weight (lbs) and distance (in)");
        String responseArray[] = response.split(" ");
        output[count] = Double.parseDouble(responseArray[0]);
        count++;
        output[count] = Double.parseDouble(responseArray[1]);
        count++;
        momentCount++;
      }
    };
    
    // delete moment arm
    
    ActionListener actionListener4 = new ActionListener(){
      public void actionPerformed(ActionEvent actionEvent){
        System.out.println("Deleting moment arm");
        Component source = (Component) actionEvent.getSource();
        Object response = JOptionPane.showInputDialog(source,
            "Select a moment to delete",
            "Select a Destination", JOptionPane.QUESTION_MESSAGE,
            null, name, name[0]);
        String reply =  (String) response;
        
        for(int i = 0; i< momentCount;i++){
          if( name[i] == reply){
          removeArrayValue(name,i);
        }
        }
      
      System.out.println(response);
      }
    };
    // Display all moments
    
    ActionListener actionListener2 = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        for(int i = 0; i<count; i+=2){
          JFrame f = new JFrame();
          final JDialog dialog = new JDialog(f, "" + (i/2+1) +" of " + count/2, true);
          dialog.setSize(700, 700);
          dialog.setLocation(600,350);
          JLabel label = new JLabel(name[i/2] +" "+ output[i] +"lb " + output[i+1] + "in");
          label.setHorizontalAlignment(SwingConstants.CENTER);
          label.setVerticalAlignment(SwingConstants.CENTER);
          dialog.getContentPane().add(label);
          Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              dialog.setVisible(false);
              dialog.dispose();
            }
          });
          timer.setRepeats(false);
          timer.start();
          
          dialog.pack();
          dialog.setVisible(true);
        }
      }
      
    };
      
    
    // Export moments
    
    ActionListener actionListener3 = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Exporting");
        newFile();
        JOptionPane pane = new JOptionPane("Exported");
      Object[] options = new String[] { "OK" };
      pane.setOptions(options);
      JDialog dialog = pane.createDialog(new JFrame(), "File conflict");
      dialog.setVisible(true);
      frame.setVisible(false);  
      }
    };
    
    
    
    button1.addActionListener(actionListener1);
    button2.addActionListener(actionListener2);
    button3.addActionListener(actionListener3);
    button4.addActionListener(actionListener4);
    Container contentPane = frame.getContentPane();
    contentPane.add(button1, BorderLayout.LINE_START);
    contentPane.add(button3, BorderLayout.PAGE_END);
    contentPane.add(button2, BorderLayout.PAGE_START);
    contentPane.add(button4, BorderLayout.LINE_END);
    frame.setSize(300, 100);
    frame.setLocation(500,330);
    frame.setVisible(true);
  };
  
 public static void newFile(){
    // build the text output
   String[] textOut =  new String[count];
   for(int i = 0; i<momentCount; i++){
     textOut[i] = (name[i] + " " + output[2*i] + " " + output[2*i+1]);
            }
    
    try{
      // Create file
      String extension = ".acWB";
      FileWriter fstream = new FileWriter("acWB/" + aircraftName + extension);
      BufferedWriter out = new BufferedWriter(fstream);
      out.write(momentCount + "\n");
      for(int i=0;i<momentCount;i++){
      out.write(textOut[i] + "\n");
      //Close the output stream
      }
      out.close();
    }
    catch (Exception e){//Catch exception if any
      System.err.println("Error: " + e.getMessage());
    }
    
  }
  public static void main(String[] args){
    defaultWindow();
    
  }
  
  // remove one array value
  
  public static String[] removeArrayValue(String[] args, int indexToRemove){
    int length = args.length;
    String[] output = args; 
    for(int i=indexToRemove;i<length-3;i+=2){
      output[i] = output[i+2];
      output[i+1] = output[i+3];
    }
    momentCount --;
    return output;
  }
}