package Unit_Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.opencv.core.Mat;

import App.Video_Processing;

public class Get_H_Layer_Test {
	
	@Test
	  / **
	  * Verify ArrayList with values
  	  * This function is a test to see if Hlayer is empty
	  * 
  	  * <P>
	  * The method doesn`t have return or parametres
	  *
	  *
  	  *
  	  * @see Void
  	  * /
	 
	public void getHlayerTestEmpty(){
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> hsvArrayList = video.convertToHSV(frames);
		ArrayList<Mat> hArrayList = video.getHlayer(hsvArrayList);
		assertFalse(hsvArrayList.isEmpty());
	}
	

	@Test
 	 / **
	  * Verify Amount of HSV matrix
  	  * This function is a test to see the size of the Hlayer
	  * 
  	  * <P>
	  * The method doesn`t have return or parametres
	  *
	  *
  	  *
  	  * @see Void
  	  * /
	public void getHlayerTestSize() {
		Video_Processing video = new Video_Processing("Vid.mp4");
		ArrayList<Mat> frames = video.readFrames();
		ArrayList<Mat> hsvArrayList = video.convertToHSV(frames);
		ArrayList<Mat> hArrayList = video.getHlayer(hsvArrayList);
		int amountOfFrames = hArrayList.size();
		assertEquals(1705, amountOfFrames);
	}

}
