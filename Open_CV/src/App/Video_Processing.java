package App;

import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_PROP_FRAME_COUNT;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import static com.googlecode.javacv.cpp.opencv_highgui.cvNamedWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

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
	
	public double readFrames(){
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Load the video file into the capture
		VideoCapture cap = new VideoCapture(videoName);

	    double amountOfFrames = cap.get(CV_CAP_PROP_FRAME_COUNT); //get the frame count
	    
	    ArrayList<Mat> list = new ArrayList<>();
	    Mat frame = new Mat();
	   
	    for(int actualFrame = 0; actualFrame < amountOfFrames; actualFrame++){
	    	cap.read(frame);
	        if (frame.empty()) 
	        	break;
	        list.add(frame);
	     }
	    
	    System.out.println("Amount of frames" + list.size());	
	    return amountOfFrames;
	}
}
