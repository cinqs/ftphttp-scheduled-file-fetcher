package eu.cisong.ftp;

public class FTPScheduler {
	private static boolean LOCAL_DIR_SCANNED;
	private boolean scanLocalDir;
	
	public void toInvoke(){
		if (LOCAL_DIR_SCANNED){
			///to scan ftp server
		} else {
			/// to scan local folder
			LOCAL_DIR_SCANNED = true;
		}
	}
	
	public void onInit(){
		LOCAL_DIR_SCANNED = !scanLocalDir;
	}
	
	public void onDestroy(){
		
	}

	public boolean isScanLocalDir() {
		return scanLocalDir;
	}

	public void setScanLocalDir(boolean scanLocalDir) {
		this.scanLocalDir = scanLocalDir;
	}
}
