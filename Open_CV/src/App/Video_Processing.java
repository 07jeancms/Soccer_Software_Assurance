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
import javax.swing.DefaultDesktopManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
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
	
	/*
	 * Verify if a video isOpen. It's must be under relative path
	 * 
	 * Parameter: -------
	 */
	public boolean videoIsOpen()  {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		boolean isOpen = false;
		try{
			// Load the video file into the capture
			VideoCapture video = new VideoCapture(videoName);
			
			if(video.isOpened())
				isOpen = true;
			
		}catch(Exception ex){
			
			isOpen = false;
		}
		return isOpen;
		
	}
	
	/*
	 * Enter a video file name using console
	 * 
	 * Parameters: -------
	 */
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
		
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		if(videoIsOpen()){
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
		return null;
	}
	
	//======================================================================
	
	
	/*
	 * Print a Mat image
	 * 
	 * Parameters: Mat
	 * A Mat type (image) to be printed
	 */
	public void printImage(String pWindowName, Mat pMat){
	    BufferedImage bufImage = null;
	    try {
	        MatOfByte matOfByte = new MatOfByte();
	        Highgui.imencode(".jpg", pMat, matOfByte); 
	        byte[] byteArray = matOfByte.toArray();
	        InputStream in = new ByteArrayInputStream(byteArray);
	        bufImage = ImageIO.read(in);

	        JFrame frame = new JFrame(pWindowName);
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
	 * Parameters: ArrayList of mat types to be converted to HSV ArrayList
	 */
	public ArrayList<Mat> convertToHSV(ArrayList<Mat> pMatArray){
		if(videoIsOpen()){
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
		return null;
	}
	
	
	//======================================================================
	
	
	/*
	 * Get the H layer from HSV Mat
	 * 
	 * Parameters: An ArrayList<Mat> Mat images with HSV format
	 */
	public ArrayList<Mat> getHlayer(ArrayList<Mat> pHSVarrayList){
		int amountOfMats = pHSVarrayList.size();
		ArrayList<Mat> hArrayList = new ArrayList<>();
		for(int actualMat = 0; actualMat < amountOfMats; actualMat++){
			
			Mat currentMatValue = pHSVarrayList.get(actualMat);
			
			// Get H from HSV
			ArrayList<Mat> list = new ArrayList<Mat>();
			Core.split(currentMatValue, list);
			Mat vMat = null;
			if (list.size() == 3)
			{
				vMat = list.get(0);
				hArrayList.add(vMat);
			}
			else
			{
				System.out.println("ERROR");
				throw new RuntimeException("Image does not split to 3 channels!");
			}
		}
		return hArrayList;
	}
	
	//======================================================================
	
	public Mat getHfromHSV(Mat pMat){
		Mat hsv = new Mat();
		Mat actualMatElement = pMat;
	    Imgproc.cvtColor(actualMatElement, hsv, Imgproc.COLOR_RGB2HSV);	
		// Get H from HSV
		ArrayList<Mat> list = new ArrayList<Mat>();
		Core.split(actualMatElement, list);
		Mat vMat = null;
		if (list.size() == 3)
		{
			vMat = list.get(0);
			return vMat;
		}
		else
		{
			System.out.println("ERROR");
			throw new RuntimeException("Image does not split to 3 channels!");
		}
	}

	/*
	 * Here we're going to find the soccer field
	 * Parameters: A mat matrix from H layer
	 * Return: Mat
	 */
	public Mat getSoccerField(Mat pHmat){
		int GREEN_COLOR = 60;
		int SENSITIVITY = 23;
		
		
		Scalar alphaMin = new Scalar(GREEN_COLOR - SENSITIVITY, 100, 50);
		Scalar alphaMax = new Scalar(GREEN_COLOR + SENSITIVITY, 255, 255);
		
		Mat destMat = new Mat();
		//Imgproc.threshold(pHmat, destMat, 100, 255, Imgproc.THRESH_TOZERO);
		Core.inRange(pHmat, alphaMin, alphaMax, destMat);
		return destMat;
	}
	
	
	//======================================================================
	
	/*
	 *This function is to normalize a Mat image between 2 values
	 *Parameters: Mat image, min range, max range
	 *Return:  
	*/
	public Mat normalize (Mat pHmat, int pFirstValue, int pSecondValue){
		Mat clone = pHmat.clone();
		Core.normalize(clone, clone, pFirstValue, pSecondValue, Core.NORM_MINMAX);
		return clone;
	}
	
	
	//======================================================================
	

	  public Mat stdfilt(ArrayList<Mat> pFrames, int pPosition) {
		  
		  Mat hLayer = getHfromHSV(pFrames.get(pPosition));
		  hLayer = normalize(hLayer, 0 , 255);
		  hLayer.convertTo(hLayer, CvType.CV_32F);
	    
		  //Standard Dsviation
		  Mat mu = hLayer.clone();
		  Imgproc.blur(hLayer, mu, new Size(5, 5));
	    
		  Mat hLayerClone = hLayer.clone();
		  Core.multiply(hLayer, hLayer, hLayerClone);
	    
		  Mat mu2 = hLayerClone.clone();
		  Imgproc.blur(hLayerClone, mu2, new Size(5, 5));
	    
		  Mat mu22 = mu.clone();
		  Core.multiply(mu, mu, mu22);
	    
		  Mat sub = mu2.clone();
		  Core.subtract(mu2, mu22, sub);
	    
		  //get local standard deviation
		  Mat std = sub.clone();
		  Core.sqrt(sub, std);
	    
		  //get local variance
		  Mat variance = std.clone();
		  Core.multiply(std, std, variance);

		  return variance;
	  }
	  

	  public double graythresh(Mat image) {
	    Mat matClone = image.clone();
	    matClone.convertTo(matClone, CvType.CV_8UC1);
	    double umbral = Imgproc.threshold(matClone, matClone, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
	    return umbral;
	  }
	  
	  public Mat im2bw(Mat image) {
	    double umbral = graythresh(image);// get optimum threshold.
	    Mat clone = image.clone();
	    Imgproc.threshold(clone, clone, umbral, 255, Imgproc.THRESH_BINARY);
	    return clone;
	  }
	
}



