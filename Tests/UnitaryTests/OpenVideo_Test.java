package Unit_Testing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.JUnit4;

import App.Video_Processing;

public class OpenVideo_Test {

	@Test
	public void openVideoTest() {
		Video_Processing video = new Video_Processing("Vid.mp4");
		boolean isOpen = video.videoIsOpen();
		assertEquals(true, isOpen);
	}

	
	
}
