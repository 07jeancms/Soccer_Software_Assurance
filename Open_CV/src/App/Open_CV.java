package App;

import java.io.IOException;
import java.util.ArrayList;

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
		
		video.printImage("RGB", frames.get(frameNumber));
		video.printImage("HSV", HSVframes.get(frameNumber));	
		video.printImage("H", h.get(frameNumber));
		
		// Soccer Field Detection
		Mat soccer_field = video.getSoccerField(h.get(frameNumber));
		video.printImage("Soccer_Field", soccer_field);
		
		

		
		Mat test = video.stdfilt(frames, frameNumber);
		video.printImage("Players", test);
		
		
		ArrayList<Mat> blobsPlayer = video.applyStdfilt(frames);
		VideoWriter playersVideo = video.writeFrames(blobsPlayer);
		
		
		System.out.println("Exit");
	}
}
