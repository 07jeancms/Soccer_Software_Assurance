package Unit_Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;
import org.opencv.core.Mat;

import App.Video_Processing;

public class Read_Frames_Test {

	@Test
	// 
 	  / **
	  * Verify ArrayList with values
  	  * This function is a test to see the read of the frame is empty
	  * 
  	  * <P>
	  * The method doesn`t have return or parametres
	  *
	  *
  	  *
  	  * @see Void
  	  * /
	public void readFramesEmpty(){
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		assertFalse(frames.isEmpty());
	}
	
	@Test
	// 
 	  / **
	  * Verify Amount of frames
  	  * This function is a test to see the size of the readFrame
  	  * This method return a normalize clon of a mat image
	  * 
  	  * <P>
	  * The method doesn`t have return or parametres
	  *
	  *
  	  *
  	  * @see Void
  	  * /
	public void readFramesTestSize() {
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		int amountOfFrames = frames.size();
		assertEquals(1705, amountOfFrames);
	}

}
