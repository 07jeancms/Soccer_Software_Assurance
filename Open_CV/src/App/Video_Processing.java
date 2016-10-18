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
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;

public class Video_Processing {
	
	private String videoName;
	private int AMOUNT_OF_FRAMES;
	private VideoCapture videoCapture;
	private VideoWriter videoWriter;
	final String outputFile="../output/writer-java.avi	";
	public Video_Processing(String pVideoName){
		videoName = pVideoName;
	}
	
	/*
	 * Verify if a video isOpen. It's must be under relative path
	 * 
	 * Parameter: -------
	 */
	/**
	 * @return
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
	
	/**
	 * @return
	 * @throws IOException
	 */
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
	
	
	
	/**
	 * @return
	 */
	public ArrayList<Mat> readFrames(){
		
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		if(videoIsOpen()){
			// Load the video file into the capture
			VideoCapture videoCap = new VideoCapture(videoName);
			videoCapture = videoCap;

		    double amountOfFrames = videoCap.get(CV_CAP_PROP_FRAME_COUNT); //get the frame count
		    AMOUNT_OF_FRAMES = (int)amountOfFrames;
		    
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
	
	
	/**
	 * @param pWindowName
	 * @param pMat
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
	
	
	/**
	 * @param pMatArray
	 * @return
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
	
	
	/**
	 * @param pHSVarrayList
	 * @return
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
	
	/**
	 * @param pMat
	 * @return
	 */
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
	
	
	/**
	 * @param pHmat
	 * @return
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
	
	
	/**
	 * @param pHmat
	 * @param pFirstValue
	 * @param pSecondValue
	 * @return
	 */
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
	
	public ArrayList<Mat> applyStdfilt(ArrayList<Mat> pArrayList){
		ArrayList<Mat> stdfiltArray = new ArrayList<>();
		for(int actualElement = 0; actualElement < AMOUNT_OF_FRAMES; actualElement++){
			Mat actualImage = stdfilt(pArrayList, actualElement);
			stdfiltArray.add(actualImage);
		}
		return stdfiltArray;
	}
	
	
	//======================================================================
	

	public void writeFrames(ArrayList<Mat> pArrayList) {
		/*
		final Size frameSize=new Size((int)videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH),(int)videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
		int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
		videoWriter=new VideoWriter(outputFile,fourcc,videoCapture.get(Videoio.CAP_PROP_FPS),frameSize,true);
		*/
		
		try {
			String fileName = "C:/myVideo.avi";
			//int fcc = CV_FOURCC('D', 'I', 'V', '3');
			final int fourCC= new VideoWriter().fourcc('D', 'I','V', '3');
			int fps = 20;
			Size frameSize=new Size((int)videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH),(int)videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
			videoWriter = new VideoWriter(fileName, fourCC, fps, frameSize);
			if(!videoWriter.isOpened()){
				System.out.println("ERROR OPENING FILE TO WRITE");
			}
			for(int i = 0; i < pArrayList.size(); i++){
				videoWriter.write(pArrayList.get(i));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}



