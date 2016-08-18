package App;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

import com.googlecode.javacv.cpp.opencv_core.*;


public class Open_CV {
	
	public static String VIDEO_PATH = "C:/Users/jeanc/Documents/Eclipse_Projects/OpenCV/";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		//boolean isOpen = openVideo();
		//System.out.println("Is open: " + isOpen);
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Load the video file into the capture
		VideoCapture cap = new VideoCapture("Vid.mp4");

	    double count = cap.get(CV_CAP_PROP_FRAME_COUNT); //get the frame count
	    
	    ArrayList<Mat> list = new ArrayList<>();
	    Mat frame = new Mat();
	   
	    for(int actualFrame = 0; actualFrame < count; actualFrame++){
	    	cap.read(frame);
	        if (frame.empty()) 
	        	break;
	        list.add(frame);
	     }
	    
	    System.out.println("Amount of frames" + list.size());	
	}
	
	
	public static boolean openVideo() throws IOException{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Load the video file into the capture
		VideoCapture capture;
		String fileName = readFileName();
		
		try{
			capture = new VideoCapture(fileName);
			//System.out.println(fileName + " is open");
			return true;
		}catch(Exception e){
			//System.out.println("Error: Openning" + fileName);
			return false;
		}
	}
	
		
	public static boolean checkFileExists(String filePath) throws IOException {
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}	
	
	public static String readFileName() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter file name: \n");
        String fileName = br.readLine();
		//System.out.println("FileName: " + fileName);
		return fileName;
	}
	
	public static int readFrames(String pFileName){
	    FrameGrabber grabber = new OpenCVFrameGrabber(pFileName);
	    int frameCounter = 0;
	    if (grabber == null)
	    {
	        System.out.println("!!! Failed OpenCVFrameGrabber");
	        return frameCounter;
	    }

	    cvNamedWindow("video_demo", CV_WINDOW_AUTOSIZE);
	    return frameCounter;
	}
	
	
}
