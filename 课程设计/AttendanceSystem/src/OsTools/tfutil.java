package OsTools;

//norm_layer/l2_normalize
//input_1
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.Tensors;
import org.tensorflow.Operation;
import org.tensorflow.Output;

public class tfutil {
	
	public static int DIMENSIONALITY = 512;
	public static int PIX = 160;
	public static float[][][][] MatToMatrix(Mat src){
		int channels = src.channels();
		 double[] pixel = new double[3];
		  float[][][] temp = new float[160][160][3];
		  float[][][][] image = new float[1][160][160][3];
		  int row = src.rows();
		  int col = src.cols();
		  for(int i=0;i<row;i++)
			  for(int j=0;j<col;j++) {
				  if(channels==3) {
					  pixel = src.get(i, j).clone();
					  temp[i][j][0] = (float)pixel[0];
					  temp[i][j][1] = (float)pixel[1];
					  temp[i][j][2] = (float)pixel[2];
				  }
			  }
		  image[0] = temp;
		 return prewhiten(image);
//		 return image;
	}
	
	public static SavedModelBundle loadModel(String filename) {
		SavedModelBundle model = SavedModelBundle.load(filename, "train");
		return model;
	}
	
	public static Session loadSession(SavedModelBundle model) {
		Session sess = model.session();
		return sess;
	}
	
	public static Output getOperation(SavedModelBundle model) {
		Operation op = model.graph().operation("embeddings");//norm_layer/l2_normalize,embeddings
		Output output = new Output(op, 0);
		return output;
	}
	
	public static double[][] getOutput(Session sess, Output op, Mat src) {
		float [][][][] image = MatToMatrix(src);
		Tensor<Float> input_x = Tensors.create(image);
		Tensor<Boolean> phase = Tensors.create(false); 
		Tensor out = sess.runner().feed("phase_train", phase).feed("input", input_x).fetch(op).run().get(0);
		float[][] result = new float[1][DIMENSIONALITY];	
		out.copyTo(result);
		double [][] newResult = floatToDouble(result);
		return newResult;
	}
	public static double linagNorm(double[][] Mat_1, double[][] Mat_2){
		double dist = 0;
		double temp;
		double [] mat_1 = Mat_1[0];
		double [] mat_2 = Mat_2[0];
		for(int i=0;i<tfutil.DIMENSIONALITY;i++) {
			temp = mat_1[i] - mat_2[i];
			dist += Math.pow(temp, 2.0);
		}
		return (double) dist;
	}
	
	public static String findStudentId(String filename) {
		String[] temp=null;
		String imageName = null;
		String studentId = null;
		temp = filename.split("\\\\");
		imageName = temp[temp.length-1];
		studentId = imageName.split("\\.")[0];
		return studentId;
	}
	
	public static void printInfo(String info) {
		System.out.println(info);
	}
	
	public static Mat readImage(String filename) {
		Mat imageMat = Imgcodecs.imread(filename, 1);
		imageMat = faceSub(imageMat);
		if(imageMat == null)
			return null;
		Mat newImage = resizeImage(imageMat);	
		return newImage;
	}
	
	public static Mat resizeImage(Mat image) {
		Size newSize = new Size(tfutil.PIX, tfutil.PIX);
		Mat newImage = new Mat();
		Imgproc.resize(image, newImage, newSize, Imgproc.INTER_CUBIC);
		return newImage;
	}
	
	public static Mat faceSub(Mat image) {
		CascadeClassifier facebook = new CascadeClassifier("data/haarcascade_frontalface_alt.xml");
		MatOfRect face = new MatOfRect();
		Mat gray = new Mat();
		Imgproc.cvtColor(image, gray, Imgproc.COLOR_RGB2GRAY);
		facebook.detectMultiScale(gray, face,1.1);
		Rect[] rects = face.toArray();
		Mat sub = new Mat();
		if (rects.length>0) {
			
			sub = image.submat(rects[0]);   //Mat sub = new Mat(image,rect);
			return sub;
		}		
		return null;
	}
	
	public static double[][] floatToDouble(float[][] f){
		double[][] d = new double[1][DIMENSIONALITY];
		for(int i=0;i<f[0].length;i++) {
			d[0][i] = (double)f[0][i];
		}
		return d;
	}
	
	public static float[][][][] prewhiten(float[][][][] image){
		int total = PIX*PIX*3;
		double sum = 0;
		for(int i=0;i<PIX;i++)
			for(int j=0;j<PIX;j++)
				for(int k=0;k<3;k++) {
//					System.out.println(i+"+"+j+"+"+k);
					sum += image[0][i][j][k];
				}
		double mean = sum/total;
//		System.out.println(mean);
		double stdSum = 0;
		for(int i=0;i<PIX;i++)
			for(int j=0;j<PIX;j++)
				for(int k=0;k<3;k++) {
					double diff = image[0][i][j][k]-mean;
					stdSum += Math.pow(diff, 2);
				}
		double std = Math.sqrt(stdSum/total) ;
//		System.out.println(std);
		double temp = 1.0 / Math.sqrt(total);
		//std_adj
		double[][][][] newArray = new double[1][PIX][PIX][3];
		for(int i=0;i<PIX;i++)
			for(int j=0;j<PIX;j++)
				for(int k=0;k<3;k++) {
					if(std>temp)
						newArray[0][i][j][k] = std;
					else
						newArray[0][i][j][k] = temp;
				}
		
		float[][][][] arrayResult = new float[1][PIX][PIX][3];
		for(int i=0;i<PIX;i++)
			for(int j=0;j<PIX;j++)
				for(int k=0;k<3;k++) {
					arrayResult[0][i][j][k] = (float) ((image[0][i][j][k] - mean) * (1/newArray[0][i][j][k]));
				}
		
		return arrayResult;
	}
}
