/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author gixxy
 */
public class FilterImage extends CommandBase {

    double totalWidth = 0;
    double totalHeight = 0;//
    ParticleAnalysisReport[] reports = null;
    ParticleAnalysisReport targetGoal=null;

    public FilterImage() {
        requires(camera);
        requires(lazySusan);
        requires(leftShootingMotors);
        //todo: enable this line
        //requires(rightShootingMotors);
        System.out.println("Filter image Init");    //States that the camera initialized
    }

    protected void initialize() {
    }

    protected void execute() {
        getImage();                 //loops getting a fresh image
        selectGoal();
        findDistance();
        findAngle();

    }

    protected boolean isFinished() {
        return false;                //never ends
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    public void getImage() {

        ColorImage pic;
        try {
            //camera.camera.wrieBrightness(7);       //Sets the camera to only accept very bright light
            pic = camera.getImageFromCamera();      //Declares pic variable
            totalWidth = pic.getWidth();
            totalHeight = pic.getHeight();
            BinaryImage thresholdHSL = pic.thresholdHSL(165, 185, 50, 90, 95, 110);      //Sets a Blue light threshold
            ///int remove = thresholdHSL.getNumberParticles() - 1;                     //Forms to leave 1 particle
            //BinaryImage bigObjectsImage = thresholdHSL.removeSmallObjects(false, remove);   //Removes all but the largest particle
            BinaryImage convexHullImage = thresholdHSL.convexHull(false);        //Fills in the bounding boxes for the targets
            // BinaryImage filteredImage = convexHullImage.particleFilter(cc);     //Applies the criteria from RobotInit
            reports = convexHullImage.getOrderedParticleAnalysisReports();        //Sets "reports" to the nuber of particles
            for (int i = 0; i < reports.length; i++) {                          //Systematically
                //ParticleAnalysisReport r = reports[i];                      //prints the 
                System.out.println("Particle: " + i + ": Center of mass x:" + //geometric center
                        reports[i].center_mass_x);                                       //of mass per particle.
            }
            System.out.println(convexHullImage.getNumberParticles() + " " + //Prints the number of particles
                    Timer.getFPGATimestamp());                          //per elapsed robot time.

            //filteredImage.free();       //
            convexHullImage.free();     //
            //bigObjectsImage.free();     //Memory leak
            thresholdHSL.free();        //preventions.
            SmartDashboard.putInt("Pic Height", pic.getHeight());
            pic.free();                 //

        } catch (NIVisionException ex) {      //
        } //Catches both possible exceptions
        catch (Exception ex) {               //that could be caused from above code
        }                                   //
    }

    // Called repeatedly when this Command is scheduled to run
    protected void findAngle() {
                       //  
        double targetLocale;                //  Variable Constructors
        double horCenter;                   //  
        
        horCenter = (totalWidth / 2);     //Finds the pixel value of the horizontal center
        targetLocale = targetGoal.center_mass_x;        //Finds the center of our target
        while (targetLocale != horCenter) {              //While we are not aimed at the goal
            if (targetLocale > horCenter) {                  //and if we are facing right
                lazySusan.setRelay(Relay.Value.kReverse);   //turn left
                SmartDashboard.putString("LazySusan", "Reverse");
            } else {                                        //if we face left
                lazySusan.setRelay(Relay.Value.kForward);   //turn right
                SmartDashboard.putString("LazySusan", "Forward");

            }
        }
        
    
    } 
    
  
public void selectGoal() {
        ParticleAnalysisReport leftGoal;    //
        ParticleAnalysisReport rightGoal;   //
        //todo set to 4 for comp
        if (reports.length < 3)
        {
            System.out.println("not enough goals");
            targetGoal = reports[0];
        }
        else{
            leftGoal = reports[1];     //Recognizes the
            rightGoal = reports[2];    //middle goals.
            double leftWidth = leftGoal.boundingRectWidth;     //Finds the widths of
            double rightWidth = rightGoal.boundingRectWidth;   //both middle goals.
            if (leftWidth <= rightWidth) {        //
                targetGoal = rightGoal;             //Decides which goal we are
            }
            if (rightWidth > leftWidth) {        //closer to and targets it.
                targetGoal = leftGoal;              //
            }
        }
}

public void findDistance(){
    double targetHeight = targetGoal.boundingRectHeight;   //Sets the height of our target.
    double targetHeightFeet =1.5;
    double vertFOV  = targetHeightFeet/targetHeight*totalHeight; // //Gets the foot equivalent of our vertical Field of View
     
    double camearVerticalFOV=48;
    double cameraHorizontalFOV=84;
    
    double targetWidth = targetGoal.boundingRectWidth;   //Sets the height of our target.
    double targetWidthFeet =2.0;
    double horFOV = targetWidthFeet/targetHeight*totalHeight; 
     
   
    double d1 =  (vertFOV / 2) / Math.tan(camearVerticalFOV/2);
    double d2 =  (horFOV / 2) / Math.tan(cameraHorizontalFOV/2);
    
    double d = (d1+d2)/2;
    
    SmartDashboard.putDouble("Distance", d);


    double launchSpeed = 60 * (d / Math.sqrt(((11 / 6) - d) / -16) / ((2 / 3) * 3.1415926));  //Calcs the required rpms for firing
    leftShootingMotors.setSetpoint(launchSpeed);
    SmartDashboard.putDouble("launchSpeed", launchSpeed);
    
    //todo uncomment
    //rightShootingMotors.setSetpoint(launchSpeed);
    
}


} // end of class
/*
 *
 * check out Brad's example code from
 * http://firstforge.wpi.edu/sf/go/doc1209;jsessionid=DE1AA2AE5AA5396063CB73C1AE17456B?nav=1
 *
 * package edu.wpi.first.wpilibj.templates;
 *
 * // to remove the comments high light all the code and press ctrl+slash
 * //import com.sun.squawk.util.Arrays; //import com.sun.squawk.util.Comparer;
 * //import edu.wpi.first.wpilibj.SimpleRobot; //import
 * edu.wpi.first.wpilibj.Timer; //import
 * edu.wpi.first.wpilibj.camera.AxisCamera; //import
 * edu.wpi.first.wpilibj.camera.AxisCameraException; //import
 * edu.wpi.first.wpilibj.image.BinaryImage; //import
 * edu.wpi.first.wpilibj.image.ColorImage; //import
 * edu.wpi.first.wpilibj.image.NIVisionException; //import
 * edu.wpi.first.wpilibj.image.ParticleAnalysisReport; // ///** // * // *
 * @author Greg Granito (Team 190) // * This sample program finds the top 3
 * camera images that are likely to be the goals // * when viewed by the axis
 * camera. You would use this data to drive the robot towards // * the
 * appropriate goal and score the tube. //
 */
//
///*
//public class CameraSample extends SimpleRobot {
//
//    AxisCamera camera;
//    private ColorImage colorImage;
//    private ParticleAnalysisReport s_particles[];
//    private final ParticleComparer particleComparer = new ParticleComparer();
//
//    /**
//     * Constructor for the sample program.
//     * Get an instance of the axis camera.
//     */
//    public CameraSample() {
//        camera = AxisCamera.getInstance();
//    }
//
//    /**
//     * the sample code runs during the autonomous period.
//     *
//     */
//    public void autonomous() {
//
//	/**
//	 * Run while the robot is enabled
//	 */
//        while (isEnabled()) {
//            if (camera.freshImage()) {	    // check if there is a new image
//                try {
//                    colorImage = camera.getImage(); // get the image from the camera
//
//                    /**
//		     * The color threshold operation returns a bitmap (BinaryImage) where pixels are present
//		     * when the corresponding pixels in the source (HSL) image are in the specified
//		     * range of H, S, and L values.
//		     */
//                    BinaryImage binImage = colorImage.thresholdHSL(242, 255, 36, 255, 25, 255);
//
//		    /**
//		     * Find blobs (groupings) of pixels that were identified in the color threshold operation
//		     */
//                    s_particles = binImage.getOrderedParticleAnalysisReports();
//
//		    /**
//		     * Free the underlying color and binary images.
//		     * You must do this since the image is actually stored
//		     * as a C++ data structure in the underlying implementation to maximize processing speed.
//		     */
//                    colorImage.free();
//                    binImage.free();
//
//		    /**
//		     * print the number of detected particles (color blobs)
//		     */
//                    System.out.println("Particles: " + s_particles.length);
//
//                    if (s_particles.length > 0) {
//			/**
//			 * sort the particles using the custom comparitor class (see below)
//			 */
//                        Arrays.sort(s_particles, particleComparer);
//
//                        for (int i = 0; i < s_particles.length; i++) {
//                            ParticleAnalysisReport circ = s_particles[i];
//
//			    /**
//			     * Compute the number of degrees off center based on the camera image size
//			     */
//                            double degreesOff = -((54.0 / 640.0) * ((circ.imageWidth / 2.0) - circ.center_mass_x));
//                            switch (i) {
//                                case 0:
//                                    System.out.print("Best Particle:     ");
//                                    break;
//                                case 1:
//                                    System.out.print("2nd Best Particle: ");
//                                    break;
//                                case 2:
//                                    System.out.print("3rd Best Particle: ");
//                                    break;
//                                default:
//                                    System.out.print((i + 1) + "th Best Particle: ");
//                                    break;
//                            }
//                            System.out.print("    X: " + circ.center_mass_x
//                                    + "     Y: " + circ.center_mass_y
//                                    + "     Degrees off Center: " + degreesOff
//                                    + "     Size: " + circ.particleArea);
//                            System.out.println();
//
//                        }
//                    }
//                    System.out.println();
//                    System.out.println();
//                    Timer.delay(4);
//                } catch (AxisCameraException ex) {
//                    ex.printStackTrace();
//                } catch (NIVisionException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            Timer.delay(0.01);
//        }
//    }
//
//
//    /**
//     * compare two particles
//     *
//     */
//    class ParticleComparer implements Comparer {
//
//        public int compare(ParticleAnalysisReport p1, ParticleAnalysisReport p2) {
//            float p1Ratio = p1.boundingRectWidth / p1.boundingRectHeight;
//            float p2Ratio = p2.boundingRectWidth / p2.boundingRectHeight;
//
//            if (Math.abs(p1Ratio - p2Ratio) < 0.1) {
//                return -(Math.abs((p1.imageWidth / 2) - p1.center_mass_x))
//                        - Math.abs(((p2.imageWidth / 2) - p2.center_mass_x));
//            } else {
//                if (Math.abs(p1Ratio - 1) < Math.abs(p2Ratio - 1)) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        }
//
//	// overloaded method because the comparitor uses Objects (not Particles)
//        public int compare(Object o1, Object o2) {
//            return compare((ParticleAnalysisReport) o1, (ParticleAnalysisReport) o2);
//        }
//    };
//}
//
// 
