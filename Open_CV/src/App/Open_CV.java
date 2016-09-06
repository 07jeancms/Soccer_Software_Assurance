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

import MathLabIntegrator.MathLabFunctions;

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
	
	public static String VIDEO_PATH = "C:/Users/jeanc/Documents/Eclipse_Projects/OpenCV/";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		/**===========================JC TEST===========================================================================
		
		Video_Processing video = new Video_Processing("VidTest.mp4");
		boolean opn = video.videoIsOpen();
		System.out.println(opn);
		
		int frameNumber = 1;
		
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> HSVframes = video.convertToHSV(frames);
		ArrayList<Mat> h = video.getHlayer(HSVframes);
		
		video.printImage("RGB", frames.get(frameNumber));
		video.printImage("HSV", HSVframes.get(frameNumber));	
		video.printImage("H", h.get(frameNumber));
		
		
		System.out.println("Exit");
		
		//===========================END JC TEST=========================================================================== **/
        
		//Creamos un objeto regresion de la clase Regresion, pasándole al constructor los dos arrays..
		double[] temperatura={5, 7, 10, 12, 16, 20, 23, 27, 19, 14, 9, 6};
        double[] ventas={9, 11, 15, 16, 20, 24, 27, 29, 22, 20, 14, 9};

        MathLabFunctions mFunctions = new MathLabFunctions(temperatura, ventas);

        //Se llama también, a la función miembro correlacion, la cual devuelve el coeficiente de correlación
        System.out.println("Indice de correlación " + mFunctions.correlation());
	}
}
