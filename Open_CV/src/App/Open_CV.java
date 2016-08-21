package App;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;
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

import java.awt.Image;
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
	
	public static String VIDEO_PATH = "C:/Users/Andres/git/Soccer_Software_Assurance/Open_CV";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		/*Video_Processing video = new Video_Processing("Vid.mp4");
=======
		
		
		//===========================JC TEST===========================================================================
		
		Video_Processing video = new Video_Processing("Vid.mp4");
		boolean opn = video.videoIsOpen();
		System.out.println(opn);
		
		int frameNumber = 1000;
		
>>>>>>> origin
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> HSVframes = video.convertToHSV(frames);
		
		video.printImage("RGB", frames.get(frameNumber));
		video.printImage("HSV", HSVframes.get(frameNumber));
		
		// Get V from HSV
		ArrayList<Mat> h = video.getHlayer(HSVframes);
		video.printImage("H", h.get(frameNumber));
		
<<<<<<< HEAD
		video.printImage(frames.get(1000));
		video.printImage(HSVframes.get(1000));
		System.out.println("Exit");*/
		Video_Processing video = new Video_Processing("Vid.mp4");
		boolean opn = video.openVideo();
		System.out.println(opn);
=======
		System.out.println("Exit");
		
		//===========================END JC TEST===========================================================================
>>>>>>> origin
	}
}
