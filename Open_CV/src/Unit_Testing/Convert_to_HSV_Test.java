package Unit_Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.opencv.core.Mat;

import App.Video_Processing;

public class Convert_to_HSV_Test {
	

	 
	@Test
	

	
	  / **
	  * Verify ArrayList with values
  	  * This function is a test to see if the convertion of HSV is empty
	  * 
  	  * <P>
	  * The method doesn`t have return or parametres
	  *
	  *
  	  *
  	  * @see Void
  	  * /
	
	public void convertToHSVtestEmpty(){
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> hsvArrayList = video.convertToHSV(frames);
		assertFalse(hsvArrayList.isEmpty());
	}

	@Test
	 
	 / **
	  * Verify Amount of HSV matrix
  	  * This function is a test to see the size of the convertion of HSV
  
	  * 
  	  * <P>
	  * The method doesn`t have return or parametres
	  *
	  *
  	  *
  	  * @see Void
  	  * /
	public void convertToHSVtestSize() {
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> hsvArrayList = video.convertToHSV(frames);
		int amountOfFrames = hsvArrayList.size();
		assertEquals(1705, amountOfFrames);
	}

}
