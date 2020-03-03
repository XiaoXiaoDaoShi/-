package User;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.opencv.core.Mat;

public class StudentQueue {
	BlockingQueue<Mat> queue = new ArrayBlockingQueue<>(50);
	private boolean started = false;
	
	
	
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
//		System.out.println(this.started);
	}

	public void addImageInfo(Mat imageInfo) {
		queue.offer(imageInfo);
	}
	
	public Mat getImageMat() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void clear() {
		queue.clear();
	}
	
//	public boolean isEmpty() {
//		return queue.isEmpty()
//	}
	
}
