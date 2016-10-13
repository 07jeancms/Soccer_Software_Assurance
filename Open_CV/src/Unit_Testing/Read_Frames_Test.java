package Unit_Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;
import org.opencv.core.Mat;

import App.Video_Processing;

public class Read_Frames_Test {

	public void readFramesEmpty(){
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		assertFalse(frames.isEmpty());
	}
	
	public void readFramesTestSize() {
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		int amountOfFrames = frames.size();
		assertEquals(1705, amountOfFrames);
	}

}
