package eu.cisong.ftp;

import org.apache.log4j.Logger;

public class FTPScheduler {
	private static boolean LOCAL_DIR_SCANNED;
	private boolean scanLocalDir;
	protected Logger logger = Logger.getLogger(FTPScheduler.class);
	
	public void toInvoke(){
		logger.info("invoked");
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
