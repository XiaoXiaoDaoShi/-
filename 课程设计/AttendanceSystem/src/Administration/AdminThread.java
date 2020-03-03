package Administration;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import OsTools.DBConnection;
import OsTools.FaceRecoginze;

public class AdminThread extends Thread{
	private Adminstrator admin = null;
	private JTextArea textArea = null;
//	private boolean isStart ;
	private FaceRecoginze fr = null;
	private DBConnection db = null;
	public AdminThread(JTextArea textArea, boolean isStart) {
		super();
		this.textArea = textArea;
	}
	
	

	public AdminThread(Adminstrator admin, JTextArea textArea) {
		super();
		this.admin = admin;
		this.textArea = textArea;
//		this.isStart = isStart;
	}






	public Adminstrator getAdmin() {
		return admin;
	}
	
	public void loadAdmin() {
		boolean flag1 = false;
		boolean flag2 = false;
//		admin = new Adminstrator();
		
		db = new DBConnection();
		if(db.isLoadSuccessfully())
			flag1 = true;			
		if(flag1) {
			textArea.append("数据库连接成功\n");
			flag1=true;
		}
		
		else {
			textArea.append("数据库连接失败\n");
		}
		
		fr = FaceRecoginze.getInstance();	
		if(fr.isLoadSuccessfully())
			flag2 = true;	
		
		if(flag2) {
			textArea.append("图像处理模块初始化成功\n");
			flag2 = true;
		}
		
		else {
			textArea.append("图像处理模块初始化失败\n");
		}
		
		
		if(flag1 && flag2) {
			admin.setStart(true);
			textArea.append("系统启动成功\n");
		}
	}	
	
	public void run() {			
		loadAdmin();
	}
}
