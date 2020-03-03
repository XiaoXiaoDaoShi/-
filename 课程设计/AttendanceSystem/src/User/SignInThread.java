package User;

public class SignInThread extends Thread{
	private ClassRoom classRoom ;

	public SignInThread(ClassRoom classRoom) {
		super();
		this.classRoom = classRoom;
	}
	
	void signIn() {
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(classRoom.signIsOver()) {
				continue;
			}
			else {
				classRoom.signIn();
			}
		}
		
	}
	
	public void run() {
		signIn();
	}
	
}
