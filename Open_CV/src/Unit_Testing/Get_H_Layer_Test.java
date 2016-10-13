package Unit_Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.opencv.core.Mat;

import App.Video_Processing;

public class Get_H_Layer_Test {
	
	 
	public void getHlayerTestEmpty(){
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> hsvArrayList = video.convertToHSV(frames);
		ArrayList<Mat> hArrayList = video.getHlayer(hsvArrayList);
		assertFalse(hsvArrayList.isEmpty());
	}
	

	public void getHlayerTestSize() {
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> hsvArrayList = video.convertToHSV(frames);
		ArrayList<Mat> hArrayList = video.getHlayer(hsvArrayList);
		int amountOfFrames = hArrayList.size();
		assertEquals(1705, amountOfFrames);
	}

}
