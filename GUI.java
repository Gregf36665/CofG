/**
 * This class is to create the main UI to make new aircraft, new WB data sheets and view the current position
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

public class GUI {
  
  
  public static void main(String[] args) { 
    defaultWindow();
  }
  
  public static void defaultWindow(){
    JFrame frame = new JFrame("Main menu");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridLayout layout=new GridLayout(2,2);  
    frame.setLayout(layout);
      
    JButton button1 = new JButton("Edit Aircraft"); 
    JButton button3 = new JButton("Edit Weight Balance Document");
    JButton button2 = new JButton("Show Weight Balance"); 
    JButton button4 = new JButton("Utilities");
    
    
          ActionListener actionListener1 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
          System.out.println("Edit Aircraft");
          String[] empty = new String[2];
          ExportAircraft.main(empty);
        }
    };
          
      ActionListener actionListener2 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
          System.out.println("Show WB");
          String[] empty = new String[2];
          Main.main(empty);
        }
    };
      
      ActionListener actionListener3 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
          System.out.println("Edit WB document");
          String[] empty = new String[2];
          ExportWBdata.main(empty);
      
        }
      };
      
      ActionListener actionListener4 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
          System.out.println("Start Utilities");
          String[] empty = new String[2];
          Utilities.main(empty);
        }
      };
    button1.addActionListener(actionListener1);
    button2.addActionListener(actionListener2);
    button3.addActionListener(actionListener3);
    button4.addActionListener(actionListener4);
    
    Container contentPane = frame.getContentPane();
    contentPane.add(button1);
    contentPane.add(button3);
    contentPane.add(button2);
    contentPane.add(button4);
    frame.setSize(410, 100);
    frame.setLocation(450,330);
    frame.setVisible(true);
  }

  
}
