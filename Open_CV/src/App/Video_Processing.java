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
	/**
  	* Returns a Boolean object that indicates whether the video is habierto or not.
	*This method doesn't receive parameters 
	* Verify if a video isOpen. It's must be under relative path
  	* The videoName indicua the name of the video that will be charged for processing
  	* <P>
	* The method always returns a value, whether the video is open or not
	*
  	* @return The value obtained if the video is open or not
  	* @see Boolean
  	* /
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
	
	//======================================================================
	
	/**
	 * @return
	 * @throws IOException
	 */
	/ **
  	* Returns a String object that indicates the name of a file.
        *This method doesn't receive parameters 
  	* Enter a video file name using console
  	* <P>
	* The method returns a value of the name of the string that was enter by the user 	
	*
  	* @return The value obtained of the name of the video
  	* @see String
  	* /
	public String readFileName() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter file name: \n");
        String fileName = br.readLine();
		//System.out.println("FileName: " + fileName);
		return fileName;
	}
	
	//======================================================================
	

	/ **
 	* Decompose a video on frames
  	* Returns a ArrayList<Mat> object that indicates a serie of frames in the video.
	*This method doesn't receive parameters
  	* <P>
	* The method returns a values of the frames in an ArrayList<Mat>  	
	*
  	* @return The value obtained of the ArrayList<Mat> of the video
  	* @see ArrayList<Mat> 
  	* /
	
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
	

	/ **
 	* Print a Mat image
  	* This method doesn't have a return, is a void method 
	* Print a sets of bytes in a frame
  	* <P>
	* The method doesn't have  returns a value  	
	*
	* @param pWindowName is a string value for de new JFrame
	* @param pMat is a Mat value for the new image
	*
  	* 
  	* @see Void
  	* /


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
	
	
	
	/ **
 	* Convert a RGB image to HSV image
  	* This method return a ArrayList<Mat> that represent a convert HSV 
	* Takes a RGB image and convert it into a HSV image
  	* <P>
	* The method return an ArrayList<Mat>   	
	*
	* @param pMatArray is a ArrayList<Mat> value used for convert a RGB image into a HSV
	* 
	*
  	* @return a ArrayList<Mat> representing the new HSV image
  	* @see ArrayList<Mat>
  	* /

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
	
	 / **
  	 * Get the H layer from HSV Mat
  	 * This method return a ArrayList<Mat> that represent a layer takes from HSV Mat
	 * Takes a layer from HSV Mat
  	 * <P>
	 * The method return an ArrayList<Mat>   	
	 *
	 * @param pHSVarrayList is a ArrayList<Mat> value, is the HSV 
	 * 
	 *
  	 * @return a ArrayList<Mat> representing the new H layer 
  	 * @see ArrayList<Mat>
  	 * /


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
	
	/ **
  	 * Get the H from HSV Mat
  	 * This method return a Mat  from HSV Mat
	 * 
  	 * <P>
	 * The method return a Mat   	
	 *
	 * @param pMat is a Mat value, is the HSV 
	 * 
	 *
  	 * @return a Mat representing the new H 
  	 * @see Mat
  	 * /

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


	 / **
  	  * Here we're going to find the soccer field
  	  * This method return a Mat  from HSV Mat
	  * 
  	  * <P>
	  * The method return a Mat, represent a soccer field 	
	  *
	  * @param pHmat is a Mat value,  A mat matrix from H layer
	  * 
	  *
  	  * @return a Mat representing the new soccer field
  	  * @see Mat
  	  * /

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
	
	

	  / **
  	  * This function is to normalize a Mat image between 2 values
  	  * This method return a normalize clon of a mat image
	  * 
  	  * <P>
	  * The method return a Mat, represent a normalize value
	  *
	  * @param pHmat is a Mat value, a value to clone
	  * @param pFirstValue, is a int value, is a parametrer of normalize funtion
	  * @param pSecondValue, is a int value, is a parametrer of normalize funtion
	  *
  	  * @return a Mat representing the new Mat
  	  * @see Mat
  	  * /

	public Mat normalize (Mat pHmat, int pFirstValue, int pSecondValue){
		Mat clone = pHmat.clone();
		Core.normalize(clone, clone, pFirstValue, pSecondValue, Core.NORM_MINMAX);
		return clone;
	}
	
	
	//======================================================================
	

	  /**
	 * @param pFrames
	 * @param pPosition
	 * @return
	 */

	  / **
  	  * This function is to normalize a Mat image between 2 values
  	  * This method return a normalize clon of a mat image
	  * 
  	  * <P>
	  * The method return a Mat, represent a normalize value
	  *
	  * @param pFrames, is a ArrayList<Mat> value, is a parametret of getHfromHSV
	  * @param pPosition, is a int value, is a parametret of getHfromHSV
	  *
  	  * @return a Mat representing the local variance
  	  * @see Mat
  	  * /
	public Mat stdfilt(ArrayList<Mat> pFrames, int pPosition) {
		  
		  Mat hLayer = getHfromHSV(pFrames.get(pPosition));
		  hLayer = normalize(hLayer, 0 , 255);
		  hLayer.convertTo(hLayer, CvType.CV_32F);
	    
		  //Standard Deviation
		  Mat multiply = hLayer.clone();
		  //Smooths an image using the kernel
		  Imgproc.blur(hLayer, multiply, new Size(5, 5));
	    
		  Mat hLayerClone = hLayer.clone();
		  Core.multiply(hLayer, hLayer, hLayerClone);
	    
		  Mat secondMultiply = hLayerClone.clone();
		  //Smooths an image using the kernel
		  Imgproc.blur(hLayerClone, secondMultiply, new Size(5, 5));
	    
		  Mat mu22 = multiply.clone();
		  //Calculates the per-element scaled product of two arrays.
		  Core.multiply(multiply, multiply, mu22);
	    
		  Mat substract = secondMultiply.clone();
		  //Calculates the per-element difference between two arrays or array and a scalar.
		  Core.subtract(secondMultiply, mu22, substract);
	    
		  //get local standard deviation
		  Mat standard = substract.clone();
		  //Normal formula
		  Core.sqrt(substract, standard);
	    
		  //get local variance
		  Mat localVariance = standard.clone();
		//Calculates the per-element scaled product of two arrays.
		  Core.multiply(standard, standard, localVariance);

		  return localVariance;
	  }
	
	//======================================================================

	
	
	
}



