package App;

import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_PROP_FRAME_COUNT;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import static com.googlecode.javacv.cpp.opencv_highgui.cvNamedWindow;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;

public class Video_Processing {
	
	private String videoName;

	public Video_Processing(String pVideoName){
		videoName = pVideoName;
	}
	
	public boolean openVideo() throws IOException{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Load the video file into the capture
		VideoCapture capture;
		
		try{
			capture = new VideoCapture(videoName);
			//System.out.println(fileName + " is open");
			return true;
		}catch(Exception e){
			System.out.println("Error: Openning" + videoName);
			return false;
		}
	}
	
	//======================================================================
	
	public static boolean checkFileExists(String filePath) throws IOException {
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}
	
	//======================================================================
	
	public String readFileName() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter file name: \n");
        String fileName = br.readLine();
		//System.out.println("FileName: " + fileName);
		return fileName;
	}
	
	//======================================================================
	
	/*
	 * Decompose a video on frames
	 * 
	 * Parameters: 
	 * This method doesn't receive parameters because we use a loop to iterate over a video using an OpenCV constant
	 */
	
	public ArrayList<Mat> readFrames(){
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Load the video file into the capture
		VideoCapture videoCap = new VideoCapture(videoName);
		
		
	    double amountOfFrames = videoCap.get(CV_CAP_PROP_FRAME_COUNT); //get the frame count
	    
	    ArrayList<Mat> frames = new ArrayList<>();
	   
	    for(int actualFrame = 0; actualFrame < amountOfFrames; actualFrame++){
	    	Mat frame = new Mat();
	    	videoCap.read(frame);
	        if (frame.empty()) 
	        	break;
	        frames.add(frame);
	     }
	    
	    System.out.println("Amount of frames: " + frames.size());	
	    return frames;
	}
	
	//======================================================================
	
	
	/*
	 * Print a Mat image
	 * 
	 * Parameters: Mat
	 * A Mat type (image) to be printed
	 */
	public void printImage(Mat m){
	    BufferedImage bufImage = null;
	    try {
	        MatOfByte matOfByte = new MatOfByte();
	        Highgui.imencode(".jpg", m, matOfByte); 
	        byte[] byteArray = matOfByte.toArray();
	        InputStream in = new ByteArrayInputStream(byteArray);
	        bufImage = ImageIO.read(in);

	        JFrame frame = new JFrame("Image");
	        frame.getContentPane().setLayout(new FlowLayout());
	        frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
	        frame.pack();
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//======================================================================
	
	
	/*
	 * Convert an RGB image to HSV image
	 * 
	 * Parameters: ArrayList of mat types to be converted to HSV Arraylist
	 */
	public ArrayList<Mat> convertToHSV(ArrayList<Mat> pMatArray){
		int pMatArraySize = pMatArray.size();
		ArrayList<Mat> arrayListHSV = new ArrayList<>();
		for(int actualMatCounter = 0; actualMatCounter < pMatArraySize; actualMatCounter++){
			Mat hsv = new Mat();
			Mat actualMatElement = pMatArray.get(actualMatCounter);
		    Imgproc.cvtColor(actualMatElement, hsv, Imgproc.COLOR_RGB2HSV);	
		    arrayListHSV.add(hsv);
		}
		return arrayListHSV;
	}
	
	
	//======================================================================
	
	
	
	
	
	
	
	
	
}
