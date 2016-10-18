package App;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.FontUIResource;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoWriter;

public class Open_CV {
	
	public static String VIDEO_PATH = "C:/Users/jeanc/Documents/Eclipse_Projects/OpenCV/";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Video_Processing video = new Video_Processing("VidTest.mp4");
		boolean opn = video.videoIsOpen();
		System.out.println(opn);
		
		int frameNumber = 1;
		
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> HSVframes = video.convertToHSV(frames);
		ArrayList<Mat> h = video.getHlayer(HSVframes);
		ArrayList<Mat> stdfiltArray = video.applyStdfilt(frames);
		//video.printImage("Players", stdfiltArray.get(34));
		
		
		System.out.println("Exit");
	}

}
