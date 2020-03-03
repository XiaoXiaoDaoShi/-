package User;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import OsTools.tfutil;


public class Camera  extends Thread{
	private static JLabel imageLabel;
	private static StudentQueue queue;
	
	public Camera(StudentQueue queue, JLabel imageLabel) {
		Camera.queue=queue;
		Camera.imageLabel = imageLabel;
	}
	
	public Camera (){}
	
	public static StudentQueue getQueue() {
		return queue;
	}

	public static void setQueue(StudentQueue queue) {
		Camera.queue = queue;
	}

	public static JLabel getImageLabel() {
		return imageLabel;
	}

	public static void setImageLabel(JLabel imageLabel) {
		Camera.imageLabel = imageLabel;
	}



	static {
		//在使用OpenCV前必须加载Core.NATIVE_LIBRARY_NAME类,否则会报错
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	

	public static void videoFace() {
		// 1 读取OpenCV自带的人脸识别特征XML文件
		CascadeClassifier facebook = new CascadeClassifier("data/haarcascade_frontalface_alt2.xml");
		// 2  特征匹配类
		MatOfRect face = new MatOfRect();
		// 3 特征匹配
		
		VideoCapture capture = new VideoCapture(0 );
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH,600);
        capture.set(Videoio.CAP_PROP_FRAME_HEIGHT,600);
		Mat image = new Mat();
		Mat faceImage = new Mat();
		Mat newImage = new Mat();
		int index=0;
		BufferedImage bImage ;
		if(capture.isOpened()) {
			while(true) {
				capture.read(image);
				if (!image.empty()) {
				facebook.detectMultiScale(image, face);
				
				ImageIcon imageIcon = new ImageIcon();
				Rect[] rects = face.toArray();
//						
				if(rects.length>0) {		
					faceImage = select(image, rects);
					if(queue.isStarted()) {
							newImage = tfutil.resizeImage(faceImage);  //480*640
							
							queue.addImageInfo(newImage);
							
					}						
					Imgproc.rectangle(image,new Point(rects[0].x, rects[0].y), new Point(rects[0].x + rects[0].width, rects[0].y + rects[0].height), new Scalar(0,255,0));										
					
					bImage = matToBufferedImage(image);
					imageIcon.setImage(bImage);
					imageLabel.setIcon(imageIcon);
				}		
				else {
					bImage = matToBufferedImage(image);
					imageIcon.setImage(bImage);
					imageLabel.setIcon(imageIcon);
				}
			}
				

			}
		}
	}
	
	public static BufferedImage matToBufferedImage(Mat imageMat) {  //mat to bufferedimage
		int cols = imageMat.cols();
		int rows = imageMat.rows();
		int elemSize = (int) imageMat.elemSize();
		byte[] data = new byte[cols * rows * elemSize];
		int type;
		imageMat.get(0, 0, data);
		switch(imageMat.channels()) {
		case 1:
			type = BufferedImage.TYPE_BYTE_GRAY;
			break;
		case 3:
			type = BufferedImage.TYPE_3BYTE_BGR;
			// BGR to RGB
			byte b;
			for(int i=0; i<data.length; i=i+3) {
				b = data[i];
				data[i] = data[i + 2];
				data[i+2] = b;
			}
			break;
			default:
				return null;
		}
		BufferedImage image2 = new BufferedImage(cols, rows, type);
		image2.getRaster().setDataElements(0, 0, cols, rows, data);
		return image2;
		
	}
	
	public void run() {
		videoFace();
	}
	
	public static Mat select(Mat image, Rect[] rects) {
		Mat sub = new Mat();
		for(int i = 0 ; i < rects.length ; i++){
			Rect rect = rects[i];
			sub = image.submat(rects[i]);   //Mat sub = new Mat(image,rect);
			
		}
		return sub;
	}
	
}
