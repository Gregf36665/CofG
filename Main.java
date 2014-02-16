import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;



public class Main extends Canvas{
  
  public static boolean CofG = false;
  public static String[] aircraftName = {"",""};
  
  public void paint(Graphics graphics){
    /**********************************/
    /***********BUILD*PLANE************/
    // import in all of the dimentions
    int radNoseV = GlobalStorage.radNoseVS; //radius of the nose vertical
    int radNoseH = GlobalStorage.radNoseHS; //radius of the nose horizontal
    int fuselageLength = GlobalStorage.fuselageLengthS; //length from nose to tail
    int fuselageHeight = GlobalStorage.fuselageHeightS; // height of main area
    int epionageLength = GlobalStorage.epionageLengthS; // length of epionage
    int epionageHeight = GlobalStorage.epionageHeightS; // height of aft epionage
    int tailPlaneHeight = GlobalStorage.tailPlaneHeightS; // height of tail plane  
    int tailPlaneBaseLength = GlobalStorage.tailPlaneBaseLengthS; // length of tailplane base
    int tailPlaneTopLength = GlobalStorage.tailPlaneTopLengthS; // length of the top of the tail plane
    int tailPlaneDifference = GlobalStorage.tailPlaneDifferenceS; // difference between edge of base and edge of top of tail plane
    int mainWingPlaneHeight = GlobalStorage.mainWingPlaneHeightS; // semi-minor axis of the main wing 
    int mainWingPlaneLength = GlobalStorage.mainWingPlaneLengthS; // semi-major axis of the main wing
    int mainWingPosH = GlobalStorage.mainWingPosHS; // distance from nose to the leading edge of the main wing
    int mainWingPosV = GlobalStorage.mainWingPosVS; // distance from the base of the fuselage to the base of the wing
    int tailWingPlaneHeight = GlobalStorage.tailWingPlaneHeightS; // semi-minor axis of the horizontal stabilizer 
    int tailWingPlaneLength = GlobalStorage.tailWingPlaneLengthS; // semi-major axis of the horizontal stabilizer
    int blockHeight = GlobalStorage.blockHeightS; //height of cockpit
    int blockLength = GlobalStorage.blockLengthS; //distance from start of block till end horizontally
    
    // Display imports 
    int nosePosX = GlobalStorage.nosePosX; // x nose position
    int nosePosY = GlobalStorage.nosePosY; // y nose position
    int scale = GlobalStorage.scale;
    
    // Start to draw
    // in general each line is a new point set (x,y)
    
    // Nose
    graphics.drawArc(nosePosX-radNoseH,nosePosY-radNoseV,radNoseH*2,radNoseV*2,-90,180); // posx,posy,dimx,dimy,start,arc
    
    // fuselage
    
    // bottom
    graphics.drawLine(nosePosX,nosePosY+radNoseV,
                      nosePosX-fuselageLength,nosePosY+radNoseV);
    // block
    graphics.drawLine(nosePosX,nosePosY-radNoseV,
                      nosePosX-blockLength,nosePosY+radNoseV-fuselageHeight);
    
    // top
    // up to leading edge
    if (fuselageHeight - mainWingPosV < mainWingPlaneHeight){
    graphics.drawLine(nosePosX-blockLength, nosePosY+radNoseV-fuselageHeight,
                      nosePosX-mainWingPosH+radNoseH, nosePosY+radNoseV-fuselageHeight);
    // from trailing edge to start of epionage 
    graphics.drawLine(nosePosX-mainWingPosH+radNoseH-mainWingPlaneLength, nosePosY+radNoseV-fuselageHeight,                  
                      nosePosX-fuselageLength, nosePosY+radNoseV-fuselageHeight);
    }
    else{
      graphics.drawLine(nosePosX-blockLength, nosePosY+radNoseV-fuselageHeight,
                        nosePosX-fuselageLength, nosePosY+radNoseV-fuselageHeight);// draw the top the entire way
    }
    // epionage section
    
    //top to the vertical OR horizontal stabilizer
    if(tailPlaneBaseLength >= tailWingPlaneLength){
    graphics.drawLine(nosePosX-fuselageLength,nosePosY-radNoseV-blockHeight,
                      nosePosX-fuselageLength-epionageLength+tailPlaneBaseLength,nosePosY-radNoseV-blockHeight); 
    }
    else {
      graphics.drawLine(nosePosX-fuselageLength,nosePosY-radNoseV-blockHeight,
                      nosePosX-fuselageLength-epionageLength+tailWingPlaneLength,nosePosY-radNoseV-blockHeight);
    }
    
    //slope
    graphics.drawLine(nosePosX-fuselageLength,nosePosY+radNoseV,
                      nosePosX-fuselageLength-epionageLength,nosePosY-radNoseV-blockHeight+epionageHeight); 
    
    //back
    graphics.drawLine(nosePosX-fuselageLength-epionageLength,nosePosY-radNoseV-blockHeight,
                      nosePosX-fuselageLength-epionageLength,nosePosY-radNoseV-blockHeight+epionageHeight);
    
    // build the main wing
    
    graphics.drawOval(nosePosX-mainWingPosH+radNoseH-mainWingPlaneLength,
                      nosePosY-radNoseV-blockHeight-(mainWingPlaneHeight/2)-mainWingPosV+fuselageHeight,
                      mainWingPlaneLength, mainWingPlaneHeight);
    
    // build the tail unit
    // build vertical stabilizer
    //trailing edge
    graphics.drawLine(nosePosX-fuselageLength-epionageLength,nosePosY+radNoseV-fuselageHeight,
                      nosePosX-fuselageLength-epionageLength-tailPlaneDifference,nosePosY+radNoseV-fuselageHeight-tailPlaneHeight);
    
    // top
    
    graphics.drawLine(nosePosX-fuselageLength-epionageLength-tailPlaneDifference,nosePosY+radNoseV-fuselageHeight-tailPlaneHeight,
                      nosePosX-fuselageLength-epionageLength-tailPlaneDifference+tailPlaneTopLength,nosePosY+radNoseV-fuselageHeight-tailPlaneHeight);
    
    // leading edge
    graphics.drawLine(nosePosX-fuselageLength-epionageLength-tailPlaneDifference+tailPlaneTopLength,nosePosY+radNoseV-fuselageHeight-tailPlaneHeight,
                      nosePosX-fuselageLength-epionageLength+tailPlaneBaseLength,nosePosY+radNoseV-fuselageHeight);
    
    // build horizontal stabilizer
    
    graphics.drawOval(nosePosX-fuselageLength-epionageLength,nosePosY+radNoseV-fuselageHeight-tailWingPlaneHeight,
                      tailWingPlaneLength,tailWingPlaneHeight);
    
    
    /**********************************************/
    /*************DRAW*C*of*G********************/
    if(CofG){
    int want = mainWingPosH+(mainWingPlaneLength/4); // 1/4 of the chord of the wing
    // highlight range
    // note the max is the total range so a max of 20 ==> 10 in for and 10 in aft
    
    // Red range (20 in for and aft)
    int maxRed = 40;
    graphics.setColor(Color.red);
    graphics.fillRect(nosePosX+radNoseH-want-(maxRed*scale)/2,nosePosY-20,maxRed*scale,40);
    // Yellow range (10 in for and aft)
    int maxYellow = 20;
    graphics.setColor(Color.yellow);
    graphics.fillRect(nosePosX+radNoseH-want-(maxYellow*scale)/2,nosePosY-20,maxYellow*scale,40);
    
    //Green Range
    int maxGreen = 10;
    graphics.setColor(Color.green);
    graphics.fillRect(nosePosX+radNoseH-want-(maxGreen*scale)/2,nosePosY-20,maxGreen*scale,40);
    
    // Desired C of G
    int blobSize = 10;
    
    graphics.setColor(Color.blue);
    graphics.fillOval(nosePosX+radNoseH-(blobSize/4)-want,nosePosY-(blobSize/4),blobSize/2,blobSize/2);
    graphics.drawOval(nosePosX+radNoseH-(blobSize/4)-want,nosePosY-(blobSize/4),blobSize/2,blobSize/2);
    
    // Current C of G
    
    graphics.setColor(Color.orange);
    graphics.fillOval(nosePosX+radNoseH-(blobSize/2)-(CofG()[0]*scale),nosePosY-(blobSize/2),blobSize,blobSize);
    graphics.drawOval(nosePosX+radNoseH-(blobSize/2)-(CofG()[0]*scale),nosePosY-(blobSize/2),blobSize,blobSize);
    
    
    }
      
     
  }
  
  
  public static void main(String[] args){
    // We initialize our class here
    Main canvas = new Main();
    
    JFrame frame = new JFrame("Airplane"); // title of window
    frame.setSize(GlobalStorage.nosePosX+200, 2*GlobalStorage.nosePosY);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    
    // Here we add the text to a new frame
    // we make the frame and set the size
    JFrame frame2 = new JFrame("Stats");
    frame2.setSize(700,60);
    // get variables for message 
    
    int mainWingPlaneLength = GlobalStorage.mainWingPlaneLength; // semi-major axis of the main wing
    int mainWingPosH = GlobalStorage.mainWingPosH; // distance from nose to the leading edge of the main wing
    
    int cOfG = CofG()[0];
    int weight = CofG()[1];
    int moment = CofG()[2];
    int want = mainWingPosH+(mainWingPlaneLength/4); // 1/4 of the chord of the wing
    
    // 
    JLabel weightLabel = new JLabel(" Weight:" + weight + "lbs. Moment:" + moment + "lbs in. Current C of G:" +
                                    cOfG + "in (orange). Desired C of G:" + want + "in (blue)");
    weightLabel.setFont(new Font("Serif", Font.PLAIN, 18));
    weightLabel.setVerticalTextPosition(JLabel.BOTTOM);
    frame2.add(weightLabel);
    
    // Find the dim of the computer
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    
    // Here we add the plane to the frame
    frame2.setLocation((screenSize.width -700)/ 2 , GlobalStorage.nosePosY*2+25);
    frame2.setVisible(true);
    
    // Find the dim of the computer
    frame.getContentPane().add(canvas);
    frame.setLocation((screenSize.width-GlobalStorage.nosePosX-200)/2,25);
    frame.setVisible(true);
  
  }
  private static double momentArm(double[] factors){
    double weight = factors[0];
    double distance = factors[1];
    return (weight*distance);
  }
  

  public static int[] CofG(){
    int scale = GlobalStorage.scale;
    CofG = true;
    int items = ImportFileMassBalance.WBlength;
    
    // Use utilities to calculate mass from foam removed
    //
    double[][] total = new double[items][2];
    
    for(int i =0; i<items; i++){
      total[i][0] = ImportFileMassBalance.selectFile(i)[0];
      total[i][1] = ImportFileMassBalance.selectFile(i)[1];
    }
    /*******************************/
    /****[Weight,Distance]*ARRAY****/
    /***DONT*FORGET*TO*UPDATE*TOTAL*/
    /***WITH*NEW*ITEMS*ADDED*OR*DEL*/
    // units [lbs,inches]
    
    
    double[] plane = {6,54.5};
    double[] wing = {9,35};
    double[] battery = {2,5};
    double[] hole = {1,70};
    
    double[] momentArms = new double [total.length];
    for(int i=0; i<items; i++){
      momentArms[i] = momentArm(total[i]);
    }
    double totalWeight = 0;
    
    for(int i=0; i<total.length;i++){
      totalWeight += total[i][0];
    }
    double totalMoment = 0;
    
    for(int i=0; i<total.length;i++){
      totalMoment += momentArms[i];
    }
    
    
    int centerOfGravity = (int)(totalMoment/totalWeight);
    
    int[] output = {centerOfGravity,(int)totalWeight,(int)totalMoment};
    return output;
  }
}