package Unit_Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.opencv.core.Mat;

import App.Video_Processing;

public class Read_Frames_Test {

	@Test
	public void readFramesTest() {
		Video_Processing video = new Video_Processing("VidTest.mp4");
		ArrayList<Mat> frames = video.readFrames();
	}

}
