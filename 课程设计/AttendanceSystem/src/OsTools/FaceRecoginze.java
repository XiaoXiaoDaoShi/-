package OsTools;
import java.io.File;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.tensorflow.Output;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;


public class FaceRecoginze {
	
	private static Session sess = null;
	private static Output op = null;
	private static SavedModelBundle model = null;
	private static FaceRecoginze fr = new FaceRecoginze();
	private  FaceRecoginze() {
		try {
		model = tfutil.loadModel("facenet_model");
		sess = tfutil.loadSession(model);
		op = tfutil.getOperation(model);
		}catch(Exception e){
			model = null;
			sess=null;
			op = null;
		}
	}
	public static FaceRecoginze getInstance() {
		if(isLoadSuccessfully())
			return fr;
		else
			return null;
	}
//	
	public static boolean isLoadSuccessfully() {
		if(model !=null && sess!=null && op!=null)
			return true;
		else
			return false;
	}
	
	public static double [][] imageTodoubleArray(Mat imageMat) {
		double[][] result = new double[1][tfutil.DIMENSIONALITY];
		result = tfutil.getOutput(sess, op, imageMat);
		return result;
	}
	
	public static Mat getImageByFileName(String filename) {
		Mat imageMat = Imgcodecs.imread(filename,1);	
		return imageMat;
	}
	
	
}
