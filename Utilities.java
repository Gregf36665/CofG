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
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.*;

import javax.swing.*;


import java.util.Scanner; 



public class Utilities{
  // note this is set up to work in lbs and inches!!!
  
  public static void main(String[] args){
    // test the functions
    defaultWindow();
  }
  
  public static double Prism4(double a, double b, double h, double depth){
    
      double area = h*(a+b)/2;
      double volume = area*depth;
      JOptionPane pane = new JOptionPane("Volume = " + volume);
      Object[] options = new String[] { "OK" };
      pane.setOptions(options);
      JDialog dialog = pane.createDialog(new JFrame(), "Trapizoid");
      dialog.setVisible(true);
      return volume; 
    } 
    
    public static double Prism3(double sideA, double sideB, double sideC, double depth){
      // recall sqrt(p(p-a)(p-b)(p-c)), where p = (a+b+c)/2 (half the perimeter)
      double p = (sideA+sideB+sideC)/2;
      double area = Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC));
      double volume = area*depth;
      JOptionPane pane = new JOptionPane("Volume = " + volume);
      Object[] options = new String[] { "OK" };
      pane.setOptions(options);
      JDialog dialog = pane.createDialog(new JFrame(), "Triangle");
      dialog.setVisible(true);
      return volume; 
    }
    
    public static double foamular150(double volume){
      double density = 1.3/(12*12*12);
      double mass = density*volume;
      JOptionPane pane = new JOptionPane("Weight = " + mass);
      Object[] options = new String[] { "OK" };
      pane.setOptions(options);
      JDialog dialog = pane.createDialog(new JFrame(), "foamular150");
      dialog.setVisible(true);
      return mass; 
    }
    
    public static double foamular250(double volume){
      double density = 1.55/(12*12*12);
      double mass = density*volume;
      JOptionPane pane = new JOptionPane("Weight = " + mass);
      Object[] options = new String[] { "OK" };
      pane.setOptions(options);
      JDialog dialog = pane.createDialog(new JFrame(), "foamular250");
      dialog.setVisible(true);
      return mass; 
    }
    
    public static void defaultWindow(){
      //Create a JFrame
      JFrame frame=new JFrame("Utilities");   
      GridLayout layout=new GridLayout(2,2);  
      frame.setLayout(layout);  
      
      
      //Create 4 buttons that we want to add into JFrame  
      JButton button1=new JButton("Volume of Triangle");  
      JButton button2=new JButton("Volume of Trapezoid");  
      JButton button3=new JButton("Mass of foamular 150");  
      JButton button4=new JButton("Mass of foamular 250");  
      
      // Set up the listeners
      
      ActionListener actionListener1 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
          Component source = (Component) actionEvent.getSource();
          String response = JOptionPane.showInputDialog(source, "Enter the three sides (in)");
          String responseArray[] = response.split(" ");
          double a = Double.parseDouble(responseArray[0]);
          double b = Double.parseDouble(responseArray[1]);
          double c = Double.parseDouble(responseArray[2]);
          String response2 = JOptionPane.showInputDialog(source, "Enter the depth (in)");
          String responseArray2[] = response2.split(" ");
          double d = Double.parseDouble(responseArray2[0]);
          Prism3(a,b,c,d);
        }
      };
      
      ActionListener actionListener2 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
      Component source = (Component) actionEvent.getSource();
      String response = JOptionPane.showInputDialog(source, "Enter the parallel side lengths and perpendicular height (in)");
      String responseArray[] = response.split(" ");
      double a = Double.parseDouble(responseArray[0]);
      double b = Double.parseDouble(responseArray[1]);
      double h = Double.parseDouble(responseArray[2]);
      String response2 = JOptionPane.showInputDialog(source, "Enter the depth (in)");
      String responseArray2[] = response2.split(" ");
      double d = Double.parseDouble(responseArray2[0]);
          Prism4(a,b,h,d);
        }
      };
      ActionListener actionListener3 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
          Component source = (Component) actionEvent.getSource();
          String response = JOptionPane.showInputDialog(source, "Enter the volume (in^3)");
          String responseArray[] = response.split(" ");
          double volume = Double.parseDouble(responseArray[0]);
          foamular150(volume);
        }
      };
      
      
      ActionListener actionListener4 = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent){
           Component source = (Component) actionEvent.getSource();
          String response = JOptionPane.showInputDialog(source, "Enter the volume (in^3)");
          String responseArray[] = response.split(" ");
          double volume = Double.parseDouble(responseArray[0]);
          foamular250(volume);
          
        }
      };
      
      button1.addActionListener(actionListener1);
      button2.addActionListener(actionListener2);
      button3.addActionListener(actionListener3);
      button4.addActionListener(actionListener4);
      
      //Add all buttons into JFrame  
      frame.add(button1);  
      frame.add(button2);
      frame.add(button3);  
      frame.add(button4);  
      
      frame.setSize(400,100);  
      frame.setLocation(450,300);
      
      //Make JFrame visible. So we can see it.  
      frame.setVisible(true);  
    } 
  }
  
